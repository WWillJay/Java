package com.example.demo.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class CopyTest {


    public static void main(String[] args) {
//        String s = "{\"returnValue\":{\"result\":\"aaa\",\"checkTime\":1586756955710,\"remark\":\"sdas\",\"failReason\":\"faisdasfas\",\"bizUserId\":\"sss\"}}";
//
//        JSON.parseObject(s)
//
//        Father father = new Son();
//        Object speak = father.speak("");
//        Son son = new Son();
//        String a = son.speak("a");
//        System.out.println(father.getClass().getSuperclass());
        Map<String, String> map = new HashMap<>();
        System.out.println(map.get("a"));
    }
}

@Data
class Father{
    private String name;
    public Object speak(Object a){
        System.out.println("i am father");
        return "a";
    }
}

@Data
class Son extends Father{
    private String age;
    @Override
    public String speak(Object b){
        return "b";
    }
    public String speak(String b){
        return "1";
    }

}


@Data
class Sources1 {
    private String name;

    private String age;

    public Sources1() {
    }

    public Sources1(String name, String age) {
        this.name = name;
        this.age = age;
    }
}

@Data
class Target1 {
    private String name;

    private String age;

}