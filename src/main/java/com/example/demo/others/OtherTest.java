package com.example.demo.others;

import java.math.BigDecimal;
import java.util.Calendar;

public class OtherTest {

    public static void main(String[] args) {
//        Calendar calendar = Calendar.getInstance();962464

//        calendar.add(Calendar.DATE, -5);
//        System.out.println(calendar.get(Calendar.DATE));

        BigDecimal a = new BigDecimal(1);
        BigDecimal b = new BigDecimal(3);
        BigDecimal c = a.divide(b).setScale(3,BigDecimal.ROUND_DOWN);
//        c.setScale(2, BigDecimal.ROUND_HALF_UP);
        System.out.println(c);

    }
}
