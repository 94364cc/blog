package thread.OnePMutiC;

/**
 * Created by Administrator on 2018/11/19.
 */
public class C {
    private MyStack stack;
    public C(MyStack stack){
        this.stack=stack;
    }
    public void popService(){
        stack.pop();
    }
}
