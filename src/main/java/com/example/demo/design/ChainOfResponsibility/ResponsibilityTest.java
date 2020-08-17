package com.example.demo.design.ChainOfResponsibility;

public class ResponsibilityTest {
    public static void main(String[] args) {
        DeptManagerHandler deptManagerHandler = new DeptManagerHandler();
        GroupLeaderHandler groupLeaderHandler = new GroupLeaderHandler();
        groupLeaderHandler.setNext(deptManagerHandler);
        groupLeaderHandler.handlerRequest(10);
    }
}
