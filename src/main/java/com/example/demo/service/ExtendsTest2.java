package com.example.demo.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class ExtendsTest2 extends DemoTest{
    @Autowired
    List<? extends DemoTest> list;
}
