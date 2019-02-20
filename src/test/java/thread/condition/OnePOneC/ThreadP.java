package thread.condition.OnePOneC;

/**
 * Created by Administrator on 2018/11/22.
 */
public class ThreadP extends Thread {

    public MyService service;
    public ThreadP(MyService service){
        this.service=service;
    }

    @Override
    public void run() {
        while(true){
            service.set();
        }
    }
}
