package com.example.demo.test;

import com.example.demo.service.DemoTest;
import com.example.demo.service.ExtendsTest2;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InitializingTest implements InitializingBean {

    @Autowired
    List<? extends DemoTest> list;

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(list);
        ExtendsTest2 test2 = new ExtendsTest2();
        System.err.println(test2.getList());

    }
}
