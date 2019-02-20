package thread.OnePOneC;

/**
 * Created by Administrator on 2018/11/15.
 */
public class ThreadP extends Thread{
    private P p;
    public ThreadP(P p){
        super();
        this.p=p;
    }

    public void run() {
        while(true){
            p.setValue();
        }
    }
}
