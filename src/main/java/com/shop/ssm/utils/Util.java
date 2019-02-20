package com.shop.ssm.utils;

import com.shop.ssm.pojo.User;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/7/23.
 */
public class Util {
    //获取随机生成的混合字母和数字的四位数
    public static String getSalt(){
        String[] element={"a","b","c","d","e","f","g","h","j","k","l","m","n","p",
                "r","s","t","u","v","w","x","y","0","1","2","3","4","5","6","7","8",
                "9"};
        List<String> list= Arrays.asList(element);
        Collections.shuffle(list);
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<list.size();i++){
            sb.append(list.get(i));
        }
        String salt=sb.toString().substring(0,4);
        return salt;
    }

    //校验密码
    public static boolean match(String str){
        String regex="^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,12}$";
        return match(regex,str);
    }

    /**
     * @param regex
     * 正则表达式字符串
     * @param str
     * 要匹配的字符串
     * @return 如果str 符合 regex的正则表达式格式,返回true, 否则返回 false;
     */
    private static boolean match(String regex, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    //将对象序列化

    public static byte[] serialize(Object object) {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            // 序列化
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (Exception e) {

        }
        return null;
    }

    //将对象反序列化
    public static Object unserialize( byte[] bytes) {
        ByteArrayInputStream bais = null;
        try {
            // 反序列化
            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {

        }
        return null;
    }

    public static void main(String[] args) {
        User user=new User();
        user.setId(1);
        user.setNick("nick");
        user.setPassword("pwd");
        byte[] serName=Util.serialize(user);
        System.out.println(serName);
        User user1= (User) Util.unserialize(serName);
        System.out.println(user1.getId());
        System.out.println(user1.getNick());
    }

}
