package thread.OnePOneC;

/**
 * Created by Administrator on 2018/11/15.
 */
public class P {
    private String lock;
    public P(String lock){
        this.lock=lock;
    }
    public void setValue(){
        try {
            synchronized (lock){
                while(!ValueObject.value.equals("")){
                    System.out.println("producer"+Thread.currentThread().getName()+" waiting");
                    lock.wait();
                }
                System.out.println("producer"+Thread.currentThread().getName()+" running");
                String value=System.currentTimeMillis()+"_"+System.nanoTime();
                System.out.println("set value="+value);
                ValueObject.value=value;
                lock.notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
