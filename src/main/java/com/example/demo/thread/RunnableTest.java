package com.example.demo.thread;

/**
 * 多线程创建
 * 方式二：实现Runnable接口
 * 1.创建一个实现Runnable接口的类
 * 2.实现Runnable接口的抽象方法run()
 * 3.创建实现类对象
 * 4.将对象作为参数传入Thread类的构造器中，来创建Thread类的对象
 * 5.通过Thread类的对象调用start()
 */
public class RunnableTest {

    public static void main(String[] args) {
        RunnableDemo runnableDemo = new RunnableDemo();
        Thread thread = new Thread(runnableDemo);
        Thread thread2 = new Thread(runnableDemo);
        Thread thread3 = new Thread(runnableDemo);
        thread.start();
        thread2.start();
        thread3.start();
    }

}

/**
 * 同步代码块解决同步安全问题
 */
class RunnableDemo implements Runnable {

    private int count = 100;

    private Object obj = new Object();

    @Override
    public void run() {

        while (true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (obj) {
                if (count > 0) {
                    System.out.println(Thread.currentThread().getName() + " : " + count);
                    count--;
                } else {
                    break;
                }
            }
        }
    }

    /**
     * 同步方法 同步监视器默认是this，所以继承Thread类的线程需要注意
     */
    private synchronized void show() {

        if (count > 0) {
            System.out.println(Thread.currentThread().getName() + " : " + count);
            count--;
        }

    }
}