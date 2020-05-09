package com.example.demo.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 解决线程安全问题的方式三：Lock锁 -- jdk 5.0新增
 */
public class LockTest {
    public static void main(String[] args) {
        RunnableBean runnableBean = new RunnableBean();
        Thread thread = new Thread(runnableBean);
        Thread thread2 = new Thread(runnableBean);
        Thread thread3 = new Thread(runnableBean);
        thread.start();
        thread2.start();
        thread3.start();
    }
}

class RunnableBean implements Runnable{

    private static int count = 100;
    /**
     * 实例化ReentrantLock
     */
    private ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true){
            try {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 上锁
                lock.lock();
                if (count > 0){
                    System.out.println(Thread.currentThread().getName() + " : " + count);
                    count--;
                } else {
                    break;
                }
            } finally {
                // 解锁
                lock.unlock();
            }
        }
    }
}