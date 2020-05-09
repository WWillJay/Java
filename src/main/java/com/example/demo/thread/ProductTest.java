package com.example.demo.thread;

import lombok.Data;

/**
 * 生产者-消费者
 */
public class ProductTest {
    public static void main(String[] args) {
        Merchandise merchandise = new Merchandise(0);
        Producer producer = new Producer(merchandise);
        Consumer consumer = new Consumer(merchandise);
        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);
        producerThread.start();
        consumerThread.start();
    }
}

class Producer implements Runnable {

    private Merchandise merchandise;

    public Producer(Merchandise merchandise) {
        this.merchandise = merchandise;
    }

    @Override
    public void run() {

        while (true) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            merchandise.increaseMerchandise();
        }
    }
}

class Consumer implements Runnable {

    private Merchandise merchandise;

    public Consumer(Merchandise merchandise) {
        this.merchandise = merchandise;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            merchandise.decreaseMerchandise();
        }
    }
}

@Data
class Merchandise {

    private int count;

    public Merchandise(int count) {
        this.count = count;
    }

    public synchronized void increaseMerchandise() {
        if (count == 20) {
            System.out.println("生产太多了，暂停生产，等待消费者消费");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            count++;
            System.out.println("生产了一个，当前个数为：" + count);
            notify();
        }


    }

    public synchronized void decreaseMerchandise() {
        if (count == 0) {
            System.out.println("当前个数为0.等待生产者生产");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            count--;
            System.out.println(Thread.currentThread().getName() + "消费了一个，当前个数为：" + count);
            notify();
        }
    }
}
