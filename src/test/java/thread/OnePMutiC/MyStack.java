package thread.OnePMutiC;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/11/19.
 */
public class MyStack {
    private List list=new ArrayList();
    synchronized public void push(){
        try {
            if(list.size()!=0){
                this.wait();
            }
            list.add("anything "+ Math.random());
            this.notifyAll();
            System.out.println("push="+list.size());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    synchronized public String pop(){
        String returnValue="";
        try {
            if(list.size()==0){
                System.out.println("pop操作中的:"+Thread.currentThread().getName()+" 线程呈wait" +
                        "状态");
                this.wait();
            }
            returnValue=""+list.get(0);
            list.remove(0);
            this.notifyAll();
            System.out.println("pop="+list.size());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return returnValue;
    }
}
