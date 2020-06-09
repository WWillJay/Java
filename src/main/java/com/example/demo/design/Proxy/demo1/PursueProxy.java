package com.example.demo.design.Proxy.demo1;

public class PursueProxy implements PursueInterface{

    private Boy boy;

    public PursueProxy() {
    }

    public PursueProxy(Boy boy) {
        this.boy = boy;
    }

    @Override
    public void pursue() {
        this.talk();
        this.gift();
        boy.pursue();
    }

    @Override
    public void gift(){
        System.out.println("代理帮忙送礼物...");
        boy.gift();
    }

    private void talk(){
        System.out.println("代理帮忙交流...");
        System.out.println("这是"+ boy.getName() + "托我帮忙的");
    }
}
