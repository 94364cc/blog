package thread.OnePMutiC;

/**
 * Created by Administrator on 2018/11/19.
 */
public class C_Thread extends Thread{
    private C c;
    public C_Thread(C c){
        super();
        this.c=c;
    }

    @Override
    public void run() {
        while(true){
            c.popService();
        }
    }
}
