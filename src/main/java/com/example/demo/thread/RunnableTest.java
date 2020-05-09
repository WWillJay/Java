package com.example.demo.thread;

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

class RunnableDemo implements Runnable {

    private int count = 100;

    @Override
    public void run() {
        while (count > 0) {
            System.out.println(Thread.currentThread().getName() + " : " + count);
            count--;
        }
    }
}