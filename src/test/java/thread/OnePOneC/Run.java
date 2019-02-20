package thread.OnePOneC;

/**
 * Created by Administrator on 2018/11/15.
 */
public class Run {
    public static void main(String[] args) throws InterruptedException {
        String lock = new String("");
        P p = new P(lock);
        C c = new C(lock);
        ThreadP[] tp = new ThreadP[2];
        ThreadC[] tc = new ThreadC[2];
        for (int i = 0; i < 2; i++) {
            tp[i] = new ThreadP(p);
            tp[i].setName("producer" + (i + 1));
            tc[i] = new ThreadC(c);
            tc[i].setName("consumer" + (i + 1));
            tp[i].start();
            tc[i].start();
        }
        Thread.sleep(5000);
        Thread[] threadArray = new Thread[Thread.currentThread().getThreadGroup().activeCount()];
        Thread.currentThread().getThreadGroup().enumerate(threadArray);//将list转成数组
        for (int i = 0; i < threadArray.length; i++) {
            System.out.println(threadArray[i].getName() + " " + threadArray[i].getState());
        }
    }
}
