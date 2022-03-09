/**
 * @author zsp
 * @version 1.0
 * @date 2022/3/9 13:56
 * 虚假唤醒问题
 */
public class WeakUpSynProblem {
    public static void main(String[] args) {
        Data data = new Data();
        new Thread(()->{
            try {
                for (int i = 0; i <10 ; i++) {
                    data.product();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"a").start();

        new Thread(()->{
            try {
                for (int i = 0; i <10 ; i++) {
                    data.consumer();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"b").start();

        new Thread(()->{
            try {
                for (int i = 0; i <10 ; i++) {
                    data.product();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"c").start();


        new Thread(()->{
            try {
                for (int i = 0; i <10 ; i++) {
                    data.consumer();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"d").start();
    }

}


class Data {
    private int num = 0;
    public synchronized void product() throws InterruptedException {
        while (num != 0) { //每次等待结束都会再次判断是否位0，不为0则继续等待
//        if (num!=0){
// 可能出现两个生产者同时获取到了同一个时间段的值，这时候都是0，只判断一次就不执行了
            wait();
        }
        num++;
        System.out.println(Thread.currentThread() + "线程生产了1份,当前:" + num);
        notifyAll();
    }

    public synchronized void consumer() throws InterruptedException {
//        while (num == 0) {
        if (num==0){
            wait();
        }
        num--;
        System.out.println(Thread.currentThread() + "线程消费了1份,当前:" + num);
        notifyAll();
    }
}

