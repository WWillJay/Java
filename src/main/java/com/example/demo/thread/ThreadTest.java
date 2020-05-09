package com.example.demo.thread;

/**
 * 多线程创建
 * 方式一
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

    @Override
    public void run() {
        while (count > 0) {
            System.out.println(Thread.currentThread().getName() + " : " + count);
            count--;
        }
    }
}
