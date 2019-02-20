package thread.condition;

/**
 * Created by Administrator on 2018/11/21.
 */
public class ThreadB extends Thread {
    private MyService service;
    public ThreadB(MyService service){
        this.service=service;
    }

    @Override
    public void run() {
        service.awaitB();
    }
}
