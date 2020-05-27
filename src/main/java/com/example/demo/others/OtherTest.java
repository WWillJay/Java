package com.example.demo.others;

import java.util.Calendar;

public class OtherTest {

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -5);
        System.out.println(calendar.get(Calendar.DATE));
    }
}
