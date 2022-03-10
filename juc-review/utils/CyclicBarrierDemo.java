package utils;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{ System.out.println("执行成功！"); });
        for (int i = 1; i <= 8; i++) {
            new Thread(()->{
                try {
                    System.out.println("执行了线程"+Thread.currentThread().getName());
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },""+i).start();
        }

    }
}

