package thread.OnePOneC;

/**
 * Created by Administrator on 2018/11/15.
 */
public class ThreadC extends Thread{
    private C c;
    public ThreadC(C c){
        super();
        this.c=c;
    }

    public void run() {
        while(true){
            c.getValue();
        }
    }
}
