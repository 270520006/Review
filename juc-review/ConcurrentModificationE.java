import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author zsp
 * @version 1.0
 * @date 2022/3/9 15:31
 */
public class ConcurrentModificationE {
    public static void main(String[] args) {
//        不论是单线程还是多线程，list触发modification错误都是因为：checkForComodification()
//        其本质都是迭代记录的初始修改次数(expectedModCount)和修改次数(modCount)不同导致
//        每个线程获取到修改次数后都会保存起来，然后进行扩容或者缩小操作都会校验两个值
//        因为modCount是volite，线程可见的，所以任何线程都可以查看到他
//        触发条件：不是扩容，也不是多线程，而是迭代器，换而言之，tostring和迭代获取

//        moreThreadListCME();
        oneThreadListCME();
//          moreThreadMapCME(); //map也和上面一样
//        safeListThread();
//        safeMapThread();
    }

    private static void safeListThread() {
        List<Object> list = new CopyOnWriteArrayList<>();
        for (int i = 1; i <= 100; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(list.toString());
            }).start();
        }
    }

    private static void safeMapThread() {
        Map<String, String> map = new ConcurrentHashMap<>();
        for (int i = 1; i <= 100; i++) {
            new Thread(() -> {
                map.put(""+(Math.random()*10),"123");
                System.out.println(map);
            }).start();
        }
    }

    public static void oneThreadListCME() {
        ArrayList<Object> list = new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            list.add(i);
        }
        for (Object o : list) {
            list.remove(o);
        }
    }

    public static void moreThreadMapCME() {
        HashMap<String, String> map = new HashMap<>();
        for (int i = 1; i <= 100; i++) {
            new Thread(() -> {
                map.put(""+(Math.random()*10),"123");
            }).start();
        }
    }

    public static void moreThreadListCME() {
        ArrayList<Object> list = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(list.toString());
            }).start();
        }
    }
}
