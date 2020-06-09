package com.example.demo.design.Proxy.demo1;

import lombok.Data;

@Data
public class Girl {
    private String name;

    public Girl(String name) {
        this.name = name;
    }
}
