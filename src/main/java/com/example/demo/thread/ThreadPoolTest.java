package com.example.demo.thread;

import java.util.concurrent.*;

/**
 * 创建线程方式四：线程池
 */
public class ThreadPoolTest {
    public static void main(String[] args) {
        // 1. 提供指定线程数量的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        executorService.submit(new CallablePool());
        executorService.execute(new ThreadPool());
        executorService.execute(new RunnablePool());
        executorService.shutdown();

    }

}

class RunnablePool implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 100 ; i++) {
            System.out.println(Thread.currentThread().getName() + " : " + i);
        }
    }
}

class ThreadPool extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100 ; i++) {
            System.out.println(Thread.currentThread().getName() + " : " + i * 2);
        }
    }
}

class CallablePool implements Callable{
    @Override
    public Object call() throws Exception {
        for (int i = 0; i < 100 ; i++) {
            System.err.println(Thread.currentThread().getName() + " : " + i * 2);
        }
        return null;
    }
}