package thread.OnePMutiC;

/**
 * Created by Administrator on 2018/11/19.
 */
public class P_Thread extends Thread {
    private P p;
    public P_Thread(P p){
        super();
        this.p=p;
    }

    @Override
    public void run() {
        while(true){
            p.pushService();
        }
    }
}
