package thread.fairLockTest;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2018/11/22.
 */
public class Service {
    private ReentrantLock lock;
    public Service(boolean isFair){
        lock=new ReentrantLock(isFair);
    }

    public void serviceMethod(){
        try{
            lock.lock();
            System.out.println("ThreadName="+Thread.currentThread().getName()+" get the lock");
        }finally {
            lock.unlock();
        }
    }
}
