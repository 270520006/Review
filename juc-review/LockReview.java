import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zsp
 * @version 1.0
 * @date 2022/3/9 10:33
 */
public class LockReview {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(()->{
         for (int i = 0; i <50 ; i++) {
//           ticket.sellTicketSyn();//synchronized锁
             ticket.sellTicketLock(); //可重入锁
         }},"A").start();
        new Thread(()->{
            for (int i = 0; i <50 ; i++) {
//                ticket.sellTicketSyn(); //synchronized锁
                ticket.sellTicketLock();//可重入锁
            }},"B").start();

    }
}


class Ticket{
    private int ticket=49;
    private int leftTicket=1;
    private Lock lock =new ReentrantLock();
    //synchronized的使用
    public synchronized   void sellTicketSyn(){
        if (ticket>0)
            System.out.println(Thread.currentThread()+"卖出了第"+(leftTicket++)+"张票"+"剩下:"+(ticket--)+"张票");
    }
    //可重入锁
    public synchronized   void sellTicketLock(){
        lock.lock();
        try {
            if (ticket>0)
                System.out.println(Thread.currentThread()+"卖出了第"+(leftTicket++)+"张票"+"剩下:"+(ticket--)+"张票");
        }finally {
            lock.unlock();
        }
    }
    //不上锁乱序
    public    void sellTicket(){
        if (ticket>0)
            System.out.println(Thread.currentThread()+"卖出了第"+(leftTicket++)+"张票"+"剩下:"+(ticket--)+"张票");
    }
}
