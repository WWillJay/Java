package com.example.demo.io;

import java.io.File;

public class IOTest {
    public static void main(String[] args) {
        File file = new File("F:\\io");
        System.out.println(file.exists());
        File[] files = file.listFiles();
        for (File f : files) {
            System.out.println(f);
        }
        System.out.println();
    }
}
