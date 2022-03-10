package queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueueDemo {
    public static void main(String[] args) {
//        testAddAndRemove();
//        testOfferAndPoll();
//        testPutAndTake();
        testTimeout();
    }


        private static void testTimeout() {
        //超时不报错，直接返回空
            ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue(3);
            try {
                System.out.println(blockingQueue.poll(1, TimeUnit.SECONDS));
                blockingQueue.offer("a", 2,TimeUnit.SECONDS);
                blockingQueue.offer("b", 2,TimeUnit.SECONDS);
                blockingQueue.offer("c", 2,TimeUnit.SECONDS);
                 System.out.println(blockingQueue.poll(1, TimeUnit.SECONDS));
                System.out.println(blockingQueue.poll(1, TimeUnit.SECONDS));

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        private static void testPutAndTake() {
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue(3);
        //take不到就一直等待着
        try {
            blockingQueue.put("a");
            blockingQueue.put("b");
            blockingQueue.put("c");

            System.out.println(blockingQueue.take());
            System.out.println(blockingQueue.take());
            System.out.println(blockingQueue.take());
            System.out.println(blockingQueue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void testOfferAndPoll() {
        //不抛出异常，有返回值
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue(3);
        blockingQueue.offer("a");
        blockingQueue.offer("b");
        blockingQueue.offer("c");

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());

    }


    public static void testAddAndRemove(){
        //爆异常，有返回值
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue(3);
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
//        System.out.println(blockingQueue.add("d")); //异常Queue full
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.element());  //弹出首部元素,弹出不算取出元素
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove()); //异常NoSuchElementException

    }
}

