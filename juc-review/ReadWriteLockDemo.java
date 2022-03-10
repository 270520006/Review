import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        for (int i = 1; i <= 5; i++) {
            final int num = i;
            new Thread(() -> {
            myCache.set(num+"",num+"");
            }).start();
        }

        for (int i = 1; i <= 5; i++) {
            final int num = i;
            new Thread(() -> {
                myCache.get(num+"");
            }).start();
        }

    }
}
class MyCache{
        private volatile Map<String,String> map=new HashMap();
        private ReadWriteLock lock=new ReentrantReadWriteLock();

        public void set(String key,String value){
                lock.writeLock().lock();

            try {
                System.out.println("准备存入键中:"+key);
                map.put(key,value);
                System.out.println(key+"存入了");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.writeLock().unlock();
            }
        }
        public void get(String key){
            lock.readLock().lock();
            try {
                System.out.println("获取到了键"+key);
                String value = map.get(key);
                System.out.println("取到了键"+key+"里的值:"+value);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.readLock().unlock();
            }
        }
}


