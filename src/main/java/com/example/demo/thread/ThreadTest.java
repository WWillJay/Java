package com.example.demo.thread;

/**
 * 多线程创建
 * 方式一：继承Thread类
 * 1.创建一个继承于Thread类的子类
 * 2.重写Thread类的run():此线程的业务逻辑
 * 3.创建子类对象
 * 4.调用对象的start()
 */
public class ThreadTest {
    public static void main(String[] args) {
        ThreadDemo threadDemo = new ThreadDemo();
        ThreadDemo threadDemo2 = new ThreadDemo();
        ThreadDemo threadDemo3 = new ThreadDemo();
        threadDemo.start();
        threadDemo2.start();
        threadDemo3.start();
    }
}

class ThreadDemo extends Thread {

    private static int count = 100;

    /**
     * 继承Thread的多线程中的对象锁必须写static
     */
    private static Object obj = new Object();

    @Override
    public void run() {
        while (true){
            synchronized (obj){
                if (count > 0){
                    System.out.println(Thread.currentThread().getName() + " : " + count);
                    count--;
                } else {
                    break;
                }
            }
        }
    }

    /**
     * 同步方法：由于同步方法默认的同步监视器是this，继承Thread类的线程必须加static，保证锁唯一
     * 此时的同步监视器是类本身
     */
    private static synchronized void show() {

        if (count > 0) {
            System.out.println(Thread.currentThread().getName() + " : " + count);
            count--;
        }

    }
}
