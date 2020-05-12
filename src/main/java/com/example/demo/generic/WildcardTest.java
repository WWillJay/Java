package com.example.demo.generic;

import java.util.ArrayList;
import java.util.List;

public class WildcardTest {


    public static void main(String[] args) {

        List<?> list = new ArrayList<>();
        List<Object> objectList = new ArrayList<>();
        List<String> stringList = new ArrayList<>();
        List<Integer> integerList = new ArrayList<>();

        System.out.println(getCount(integerList));
        System.out.println(getCount(list));
        System.out.println(getCount(stringList));


        List<? extends B> bExtends = new ArrayList<>();
        List<? super B> bSuper = new ArrayList<>();

        List<A> aArrayList = new ArrayList<>();
        List<B> bArrayList = new ArrayList<>();
        List<C> cArrayList = new ArrayList<>();

        bExtends = cArrayList;
//        C b = bExtends.get(0);
        bSuper = aArrayList;
//        A object = bSuper.get(0);


    }

    /**
     * T<A>、T<B>共同父类：T<?>
     */
    private static int getCount(List<?> list){
        return list == null ? 0 : list.size();
    }

}
class A {}

class B extends A{}

class C extends B{}