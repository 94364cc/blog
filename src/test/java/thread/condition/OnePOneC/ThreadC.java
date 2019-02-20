package thread.condition.OnePOneC;

import thread.condition.OnePOneC.MyService;

/**
 * Created by Administrator on 2018/11/22.
 */
public class ThreadC extends Thread {
    private MyService service;
    public ThreadC(MyService service){
        this.service=service;
    }

    @Override
    public void run() {
        while(true){
            service.get();
        }
    }
}
