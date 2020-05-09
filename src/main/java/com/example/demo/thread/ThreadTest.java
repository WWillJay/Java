package com.example.demo.thread;

import static java.lang.Thread.sleep;

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
//        MyFirstThread myFirstThread = new MyFirstThread();
//        myFirstThread.start();
//        for (int i = 0; i < 100; i++) {
//            try {
//                sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(Thread.currentThread().getName());
//        }
//
//        new Thread(){
//            @Override
//            public void run() {
//                System.out.println(Thread.currentThread().getName());
//            }
//        }.start();
        // 3.创建实现类对象
        MySecondThread mySecondThread = new MySecondThread();
        // 4.将对象作为参数传入Thread类的构造器中，来创建Thread类的对象
        Thread thread = new Thread(mySecondThread);
        // 5.通过Thread类的对象调用start()
        thread.start();
    }
}

class MyFirstThread extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName());
        }
    }

    @Override
    public State getState() {
        return super.getState();
    }
}

/**
 * 1.创建一个实现Runnable接口的类
 */
class MySecondThread implements Runnable{
    /**
     * 2.实现Runnable接口的抽象方法run()
     */
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName());
        }
    }
}