package com.example.demo.web.controller;

import com.example.demo.web.model.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("student")
public class StudentController {

    @RequestMapping("getStudent")
    public Student getStudent(){
        Student student = new Student();
        student.setAge(22);
        student.setName("Mary");
        System.out.println(student);
        return student;
    }
    @RequestMapping("findStudent")
    public Student findStudent(){
        Student student = new Student();
        student.setAge(18);
        student.setName("Kangkang");
        System.out.println(student);
        return student;
    }

    @GetMapping("/getContextStudent")
    public Student getContextStudent(HttpServletRequest request) {
        ServletContext application = request.getServletContext();
        return (Student) application.getAttribute("student");
    }

    @GetMapping("/total")
    public String getTotalUser(HttpServletRequest request) {
        Integer count = (Integer) request.getSession().getServletContext().getAttribute("count");
        return "当前在线人数：" + count;
    }
}
