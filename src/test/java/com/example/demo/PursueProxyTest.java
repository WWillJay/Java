package com.example.demo;

import com.example.demo.design.Proxy.demo1.Boy;
import com.example.demo.design.Proxy.demo1.Girl;
import com.example.demo.design.Proxy.demo1.PursueProxy;
import org.junit.Test;

public class PursueProxyTest {
    @Test
    public void test(){
        Girl xiaohong = new Girl("小红");

        PursueProxy proxy = new PursueProxy(new Boy("康康", xiaohong));
        proxy.pursue();
    }
}
