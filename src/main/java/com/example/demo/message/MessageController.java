package com.example.demo.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("message")
@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    @RequestMapping("test1")
    public void test(){
        messageService.test();
    }
}
