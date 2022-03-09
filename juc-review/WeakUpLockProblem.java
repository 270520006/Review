import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zsp
 * @version 1.0
 * @date 2022/3/9 13:56
 * 虚假唤醒问题
 */
public class WeakUpLockProblem {
    public static void main(String[] args) {
        Data2 data2 = new Data2();
        new Thread(()->{
            try {for (int i = 0; i <10 ; i++) { data2.printA(); } } catch (InterruptedException e) { e.printStackTrace(); } },"A").start();
        new Thread(()->{
            try { for (int i = 0; i <10 ; i++) { data2.printB(); }} catch (InterruptedException e) { e.printStackTrace(); } },"B").start();
        new Thread(()->{
        try { for (int i = 0; i <10 ; i++) { data2.printC(); } } catch (InterruptedException e) { e.printStackTrace(); } },"C").start();
    }

}


class Data2 {
    private int num = 1;
    Lock lock=new ReentrantLock();
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();
    public  void printA() throws InterruptedException {
        lock.lock();
        while (num != 1) {
            condition1.await();
        }
        try {
            num = 2;
            System.out.println(Thread.currentThread() + "输出了：AAAAAAAAA");
            condition2.signal();
        }finally {
            lock.unlock();
        }
    }

    public  void printB() throws InterruptedException {
        lock.lock();
        while (num != 2) {
            condition2.await();
        }
        try {
            num = 3;
            System.out.println(Thread.currentThread() + "输出了：BBBBBB");
            condition3.signal();
        }finally {
            lock.unlock();
        }
    }



    public  void printC() throws InterruptedException {
        lock.lock();
        while (num != 3) {
            condition3.await();
        }
        try {
            num = 1;
            System.out.println(Thread.currentThread() + "输出了：CCCCCCCC");
            condition1.signal();
        }finally {
            lock.unlock();
        }
    }
}

