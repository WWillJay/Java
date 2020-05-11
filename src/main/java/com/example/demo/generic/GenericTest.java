package com.example.demo.generic;

import lombok.Data;

public class GenericTest {

    public static void main(String[] args) {
        GenericDemo demo = new GenericDemo();
        demo.setDemo("aaa");
        demo.setDemo(1);
        System.out.println(demo.getDemo().getClass());

        SubGenericDemo subGenericDemo = new SubGenericDemo();
        subGenericDemo.setDemo("1");
        System.out.println(subGenericDemo.getDemo().getClass());

    }
}

@Data
class GenericDemo<T, M>{
    T demo;
    public <E> E getE(E e){
        return e;
    }
}

@Data
class SubGenericDemo<M> extends GenericDemo<Integer, M>{
    String SubDemo;
}

