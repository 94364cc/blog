package thread.threadLocal;

import java.util.Date;

/**
 * Created by Administrator on 2018/11/20.
 */
public class ThreadLocalExt extends ThreadLocal {
    @Override
    protected Object initialValue() {
        return new Date().getTime();
    }
}
