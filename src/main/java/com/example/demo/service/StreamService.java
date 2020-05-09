package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Configuration
@EnableScheduling
public class StreamService {

    private static int NUM = 0;
//
//    @Async
//    @Scheduled(cron = "0/2 * * * * ?")
    public void aaa() throws Exception {
        System.out.println("aaa");
        if (NUM < 10){
            Thread.sleep(10000);
            System.out.println( "第"+NUM++ + "次睡醒");
        }
    }
//
//    @Async
//    @Scheduled(fixedDelay=2000)
    public void bbb() throws InterruptedException {
        System.out.println("bbb");

    }
    public static void main(String[] args) {

        List<String> stringList = new ArrayList<>();
        stringList.add("aa");
        stringList.add(new String("aa"));
        List<String> collect1 = stringList.stream().distinct().collect(Collectors.toList());
        System.out.println(collect1);

        List<Long> a = new ArrayList<>();
        a.removeAll(new ArrayList<>());
        a.add(new Long(1));
        List<Long> b = new ArrayList<>();
        b.add(new Long(1));
        b.add(188L);

        b.addAll(a);
        System.out.println(b.toString());
        List<Long> collect = b.stream().distinct().collect(Collectors.toList());
        System.out.println(collect.toString());

        String strs = "abc";
        System.out.println("------" + strs.contains("d"));

//        String a = "13132257660";
//        System.out.println(a.substring(7, 11));
//        int b = 10;
//        System.out.println(b++);
//        System.out.println(b);
//        List<String> strList = new ArrayList<>();
//
//        if (strList != null){
//            System.out.println(strList.toString().substring(1,strList.toString().length()-1));
//        }
        Integer i = 1500;
        System.out.println(i*0.85);
        Set<String> strings = new HashSet<>();
        strings.add("ac");
        strings.add("bb");
        strings.add("ac");
        List<String> strings1 = Arrays.asList("ac","bb");
        System.out.println(strings.containsAll(strings1));
        System.out.println(strings1.containsAll(strings));
    }

    private static List<User> list = Arrays.asList(

    );

    public static void test1(){
        list.stream().filter(x -> x.getAge() < 14).sorted(Comparator.comparingInt(User::getAge)).forEach(System.out::println);
    }

    public static void test2(){
        List<User> reList = list.stream().filter(x -> x.getAge() > 12).sorted(Comparator.comparingInt(User::getAge)).collect(Collectors.toList());
        reList.forEach(x -> System.out.println(x.toString()));
    }

    public static void test3(){
        list.stream().sorted(Comparator.comparingInt(User::getAge)).map(User::getName).forEach(System.out::println);
    }


}
class Animal{
    public String name;
}

class Bin extends Animal{
    public String talk;

    public Bin(){}

    public Bin(String talk){
        this.talk = talk;
    }
}

class Main {
    public static void main(String[] args) {
        Bin bin = new Bin();
        test(bin);
        System.out.println(bin.name);
    }

    private static void test(Animal animal){
        animal.name = "aaa";
    }

}

class AtomaticTest implements Runnable {

    private String name;

    private static AtomicBoolean exists = new AtomicBoolean(false);

    public AtomaticTest(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        if(exists.compareAndSet(false, true)) {
            System.out.println(name + ":enter");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + ":leave");
            exists.set(false);
        }else{
            System.out.println(name +":give up");
        }
    }

    public static void main(String[] args) {
        AtomaticTest atomatic1 = new AtomaticTest("bar1");
        AtomaticTest atomatic2 = new AtomaticTest("bar2");
        new Thread(atomatic1).start();
        new Thread(atomatic2).start();
    }
}