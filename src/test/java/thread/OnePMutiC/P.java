package thread.OnePMutiC;

/**
 * Created by Administrator on 2018/11/19.
 */
public class P {
    private MyStack stack;
    public P(MyStack stack){
        this.stack=stack;
    }
    public void pushService(){
        stack.push();
    }
}
