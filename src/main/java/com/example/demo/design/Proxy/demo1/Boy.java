package com.example.demo.design.Proxy.demo1;

import lombok.Data;

@Data
public class Boy implements PursueInterface {

    private String name;

    private Girl girl;

    public Boy() {
    }

    public Boy(String name, Girl girl) {
        this.name = name;
        this.girl = girl;
    }

    @Override
    public void pursue() {
        System.out.println(girl.getName() + "和我交往吧");
    }

    @Override
    public void gift() {
        System.out.println(girl.getName() + "这是我给你的礼物");
    }
}
