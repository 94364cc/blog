package thread.OnePOneC;

/**
 * Created by Administrator on 2018/11/15.
 */
public class C {
    private String lock;
    public C(String lock){
        this.lock=lock;
    }
    public void getValue(){
        try {
            synchronized (lock){
                while(ValueObject.value.equals("")){
                    System.out.println(""+Thread.currentThread().getName()+" waiting");
                    lock.wait();
                }
                System.out.println(""+Thread.currentThread().getName()+" running");
                System.out.println("get value="+ValueObject.value);
                ValueObject.value="";
                lock.notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
