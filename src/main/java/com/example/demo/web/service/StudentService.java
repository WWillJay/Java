package com.example.demo.web.service;

import com.example.demo.web.model.Student;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    public Student getStudent(){
        Student student = new Student();
        student.setName("LiLi");
        student.setAge(8);
        return student;
    }
}
