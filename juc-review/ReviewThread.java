import pojo.Student;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author zsp
 * @version 1.0
 * @date 2022/3/9 9:37
 */
public class ReviewThread {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //实现线程方法1：实现runable
        MyRunable myRunable = new MyRunable();
        new Thread(myRunable).start();
        //实现线程方法2: 实现callable
        MyCallable myCallable = new MyCallable();
        FutureTask task = new FutureTask(myCallable);
        new Thread(task).start();
        System.out.println(task.get());//必须要使用过线程才可以获取到返回值
        //实现线程方法3：使用lambda表达式
        new Thread(()->{
            System.out.println("lambda实现线程");
        }).start();

     }
}

class MyRunable implements Runnable{
    @Override
    public void run() {
        System.out.println("执行了runable");
    }
}

class MyCallable implements Callable{

    @Override
    public Student call() throws Exception {
        return new Student(21,"zsp");
    }
}
