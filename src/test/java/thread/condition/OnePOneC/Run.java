package thread.condition.OnePOneC;

import thread.condition.OnePOneC.MyService;

/**
 * Created by Administrator on 2018/11/22.
 */
public class Run {
    public static void main(String[] args) {
        MyService service=new MyService();
        ThreadP p=new ThreadP(service);
        p.setName("P");
        ThreadC c=new ThreadC(service);
        c.setName("C");
        p.start();
        c.start();
    }
}
