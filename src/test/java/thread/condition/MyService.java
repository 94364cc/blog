package thread.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2018/11/21.
 */
public class MyService {
    private ReentrantLock lock =new ReentrantLock();
    private Condition conditionA=lock.newCondition();
    private Condition conditionB=lock.newCondition();
    public void awaitA(){
        try {
            lock.lock();
            System.out.println("begin await 时间为"+System.currentTimeMillis()+"ThreadName="+Thread.currentThread().getName());
            conditionA.await();
            System.out.println("end await 时间为"+System.currentTimeMillis()+"ThreadName="+Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void awaitB(){
        try {
            lock.lock();
            System.out.println("begin await 时间为"+System.currentTimeMillis()+"ThreadName="+Thread.currentThread().getName());
            conditionB.await();
            System.out.println("end await 时间为"+System.currentTimeMillis()+"ThreadName="+Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void signalA(){
        try{
            lock.lock();
            System.out.println("signalA 时间为"+System.currentTimeMillis()+"ThreadName="+Thread.currentThread().getName());
            conditionA.signal();
        }finally {
            lock.unlock();
        }
    }
    public void signalB(){
        try{
            lock.lock();
            System.out.println("signalAll 时间为"+System.currentTimeMillis()+"ThreadName="+Thread.currentThread().getName());
            conditionB.signal();
        }finally {
            lock.unlock();
        }
    }
}
