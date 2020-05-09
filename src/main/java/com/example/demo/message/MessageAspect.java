package com.example.demo.message;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.*;

@Aspect
public class MessageAspect {
    @Pointcut("execution (* com.example.demo.message..*.*(..))")
    public void aspect(){
    }
    @Around("aspect()")
    public void doBefore(JoinPoint joinPoint) {
        System.out.println("==========前置通知===============");
        aspect();
    }

    public static void main(String[] args) {
        Date now = new Date();
        System.out.println(now.getTime());
        now.setTime(now.getTime()-(now.getTime()%1000));
        System.out.println(now.getTime());
        System.out.println(1577932793%1000);
        System.out.println(10%3);
        System.out.println(10/3);
        Map<Integer, List<String>> map = new HashMap<>();
        map.put(1, Arrays.asList("aaa", "bbb"));
        List<String> strings = new ArrayList<>();
        System.out.println(map.get(666));
        strings.addAll(map.get(666) == null ? new ArrayList<>() : map.get(666));
        System.out.println(strings.size());

    }
}
