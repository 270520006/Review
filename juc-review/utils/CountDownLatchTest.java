package utils;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        //初始值可以小于需要跑的线程数
        // 因为源码里是将线程放入队列
        // 然后一直遍历队列到头部结点
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 1; i <=8 ; i++) {
            Thread.sleep(1000);
            new Thread(()->{
                 System.out.println("走了"+Thread.currentThread().getName()+"个学生");
                countDownLatch.countDown();
            },""+i).start();
        }
        countDownLatch.await();
        System.out.println("人都走光了，教室关门");
    }
}

