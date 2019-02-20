package thread.threadLocal;

/**
 * Created by Administrator on 2018/11/20.
 */
public class Run {
    public static void main(String[] args) {
//        try {
////            ThreadA a = new ThreadA();
////            ThreadB b = new ThreadB();
////            a.start();
////            b.start();
//            System.out.println(Tools.t1.get());
//            for (int i = 0; i < 2; i++) {
////                Tools.t1.set("Main" + (i + 1));
////                System.out.println("Main get value=" + Tools.t1.get());
//                Thread.sleep(2000);
//            }
//            System.out.println(Tools.t1.get());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        String url="/authority/add";
        url=url.substring(1);
        url=url.substring(0,url.indexOf("/"));
        System.out.println(url);
    }
}
