package com.example.demo.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
/**
 * 创建线程的方式三：实现Callable接口。 --- JDK 5.0新增
 * 如何理解实现Callable接口的方式创建多线程比实现Runnable接口创建多线程方式强大？
 * 1. call()可以有返回值的。
 * 2. call()可以抛出异常，被外面的操作捕获，获取异常的信息
 * 3. Callable是支持泛型的
 *
 */
public class CallableTest {
    public static void main(String[] args) {
        // 3.创建Callable实现类的对象
        CallableDemo callableDemo = new CallableDemo();
        // 4.将Callable实现类的对象作为参数传递至FutureTask构造器中，创建FutureTask对象
        FutureTask futureTask = new FutureTask<>(callableDemo);
        // 5.将FutureTask对象作为参数传到Thread构造器中，创建Thread对象，并调用start()
        new Thread(futureTask).start();
        try {
            // 6.获取Callable中call()的返回值，及FutureTask中get()
            Object o = futureTask.get();
            System.out.println(o);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}

/**
 * 1.创建一个实现Callable的实现类
 */
class CallableDemo implements Callable {
    private static int count = 100;

    /**
     * 2.实现call()，业务逻辑声明其中
     */
    @Override
    public Object call() throws Exception {
        int num = 0;
        while (true){
            if (count > 0){
                System.out.println(Thread.currentThread().getName() + "   " + count);
                num += count;
                count--;
            }else {
                return num;
            }
        }
    }
}
