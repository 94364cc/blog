package thread.fairLockTest;

/**
 * Created by Administrator on 2018/11/22.
 */
public class RunFair {
    public static void main(String[] args) throws InterruptedException {
        final Service service=new Service(false);
        Runnable runnable=new Runnable() {
            public void run() {
        System.out.println("Thread"+Thread.currentThread().getName()+" is running");
        service.serviceMethod();
    }
};
        Thread[] threadArray=new Thread[10];
        for (int i=0; i<threadArray.length;i++){
            threadArray[i]=new Thread(runnable);
        }
        for (int i=0; i<threadArray.length;i++){
            threadArray[i].start();
        }
    }
}
