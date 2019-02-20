package thread.condition;

/**
 * Created by Administrator on 2018/11/21.
 */
public class ThreadA extends Thread {
    private MyService service;
    public ThreadA(MyService service){
        this.service=service;
    }

    @Override
    public void run() {
        service.awaitA();
    }
}
