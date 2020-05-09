package com.example.demo.model;

import lombok.Builder;
import lombok.Data;


@Data
public class User {
    private String name;
    private Integer id;
    private Integer age;
    public User(){

    }
}
