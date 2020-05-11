package com.example.demo.generic.dao;

import com.example.demo.generic.model.Person;

public class DaoTest {
    public static void main(String[] args) {
        PersonDao imp = new PersonDao();
        System.out.println(imp.selectOne(new Person("Jack", 12)));

        Father father = new Father();
        Son<Object> son = new Son<>();
        father = son;

    }
}


class Father<T>{

}

class Son<T> extends Father<T>{

}