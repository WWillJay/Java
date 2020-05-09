package com.example.demo.thread.exercise;

import lombok.Data;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 银行有个账户，两个用户分别向同一个账户存3000元，一次存1000，存3次。每次打印金额
 */
public class Exercise01 {

    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount(0);
        BankAccountSave bankAccountSave = new BankAccountSave(bankAccount);
        Thread thread = new Thread(bankAccountSave);
        thread.setName("账户1");
        Thread thread2 = new Thread(bankAccountSave);
        thread2.setName("账户2");

        thread.start();
        thread2.start();
    }

}

class BankAccountSave implements Runnable {

    private BankAccount bankAccount;

    private ReentrantLock lock = new ReentrantLock(false);

    public BankAccountSave(BankAccount bankAccount){
        this.bankAccount = bankAccount;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(10);
                lock.lock();
                bankAccount.save(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}

/**
 * 银行账户
 */
@Data
class BankAccount {
    private int account;

    public BankAccount(int account){
        this.account = account;
    }

    public void save(int money){
        this.account += money;
        System.out.println(Thread.currentThread().getName() + "存钱成功，当前余额：" + account);
    }
}