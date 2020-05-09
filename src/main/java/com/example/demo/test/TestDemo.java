package com.example.demo.test;

import lombok.Data;

public class TestDemo {

    private static final Student a = new Student();

    public static void main(String[] args) {
        a.setName("");
    }
}

@Data
class Student {
    private String name;
}