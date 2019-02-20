package thread.condition.OnePOneC;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2018/11/22.
 */
public class MyService {
    private Lock lock =new ReentrantLock();
    private Condition condition=lock.newCondition();
    boolean hasValue=false;
    public void set(){
        try {
            lock.lock();
            System.out.println("thread name"+Thread.currentThread().getName()+" set value");
            while(hasValue)condition.await();
            hasValue=true;
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void get(){
        try {
            lock.lock();
            System.out.println("thread name" + Thread.currentThread().getName() + " get value");
            while (!hasValue){
                condition.await();
            }
            hasValue=false;
            condition.signal();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
