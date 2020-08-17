package com.example.demo.design.ChainOfResponsibility;

import lombok.Data;

@Data
public abstract class AbstractHandler {

    private AbstractHandler next;

    public abstract void handlerRequest(Integer days);

}
