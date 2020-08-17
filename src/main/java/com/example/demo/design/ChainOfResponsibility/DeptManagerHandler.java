package com.example.demo.design.ChainOfResponsibility;

public class DeptManagerHandler extends AbstractHandler {
    @Override
    public void handlerRequest(Integer days) {
        System.out.println("当前流程到--部门经理" +
                "");
        if (days > 3 && days <= 7){
            System.out.println("部门经理处理" + days + "天的请假申请");
        } else {
            if (super.getNext() == null) {
                System.out.println("部门经理无权处理，无人处理");
            } else {
                System.out.println("部门经理无权处理，进入下一个流程");
                super.getNext().handlerRequest(days);
            }
        }
    }
}
