package com.shop.ssm.utils;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by Administrator on 2017/7/23.
 */
public class FileUtil {

    /**
     * 实现文件下载
     * @param fileName
     * @param fileType
     * @param path
     * @param response
     * @return
     * @throws IOException
     */
    public static boolean  downFile(String fileName,String fileType,String path,HttpServletResponse response) throws IOException {
        File file=new File(path,fileName);
        //设置文件类型(这样设置就不止是下Excel文件了，一举多得)
        if(fileType.equals(".pdf")){
            response.setContentType("appliaction/pdf;charset=GBK");
        }else if(fileType.equals(".csv")){
            response.setContentType("appliaction/msexcel;charset=GBK");
        }else if(fileType.equals(".docx")){
            response.setContentType("appliaction/msword;charset=GBK");
        }else if(fileType.equals(".xls")){
            response.setContentType("appliaction/msexcel;charset=GBK");
    }
    //文件名
    response.setHeader("Content-Disposition", "attachment;filename=\""
            + new String(fileName.getBytes(), "ISO8859-1") + "\"");
    response.setContentLength((int) file.length());
    byte[] buffer =new byte[4096];//缓冲区
    BufferedOutputStream bos=null;
    BufferedInputStream bis=null;
        try {
            bos=new BufferedOutputStream(response.getOutputStream());
            bis=new BufferedInputStream(new FileInputStream(file));
            int i=-1;
            while((i=bis.read(buffer,0,4096))>-1){
                bos.write(buffer,0,i);
            }
            bos.flush();//不可少
            response.flushBuffer();//不可少
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (bis!=null){
                bis.close();
            }
            if (bos!=null){
                bos.close();;
            }

        }
        return false;
    }
}
