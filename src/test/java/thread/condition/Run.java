package thread.condition;

/**
 * Created by Administrator on 2018/11/21.
 */
public class Run {
    public static void main(String[] args) throws InterruptedException {
        MyService service=new MyService();
        ThreadA a=new ThreadA(service);
        a.setName("A");
        a.start();
        ThreadB b=new ThreadB(service);
        b.setName("B");
        b.start();
        Thread.sleep(3000);
        service.signalA();
    }
}
