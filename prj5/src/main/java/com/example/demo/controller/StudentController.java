package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.StudentService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/student")
@AllArgsConstructor
public class StudentController {
    
    private final StudentService studentService;




}
