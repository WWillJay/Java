package com.example.demo.thread;

/**
 * 单例模式之懒汉式的线程安全问题
 */
public class SingletonSynchronize {

    private static SingletonSynchronize instance = null;

    private SingletonSynchronize(){}

    public static SingletonSynchronize getInstance(){
        if (instance == null) {
            instance = new SingletonSynchronize();
        }
        return instance;
    }
//
//    /**
//     * 同步方法：由于是静态方法，同步监视器就是类本身，即是唯一的
//     */
//    public static synchronized SingletonSynchronize getInstance(){
//        if (instance == null) {
//            instance = new SingletonSynchronize();
//        }
//        return instance;
//    }
//
//    /**
//     * 同步代码块方式一：效率低
//     */
//    public static SingletonSynchronize getInstance(){
//        synchronized (SingletonSynchronize.class){
//            if (instance == null) {
//                instance = new SingletonSynchronize();
//            }
//        }
//        return instance;
//    }
//    /**
//     * 同步代码块方式二：效率高
//     */
//    public static SingletonSynchronize getInstance(){
//        if (instance == null) {
//            synchronized (SingletonSynchronize.class){
//                if (instance == null) {
//                    instance = new SingletonSynchronize();
//                }
//            }
//        }
//        return instance;
//    }
}
