package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {
    
    @Autowired
    private StudentService service;

    @GetMapping("")
    public ResponseEntity<List<Student>> findAll(){

        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
        
    }

    @PostMapping("/add")
    public ResponseEntity<Student> add(@RequestBody Student student){

        return new ResponseEntity<>(service.addStudent(student), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Student> delete(@PathVariable("id") Long id){

        return new ResponseEntity<>(service.removeById(id), HttpStatus.OK);
    }



}
