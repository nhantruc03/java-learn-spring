package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }


    @GetMapping()
    public ResponseEntity<List<Student>> getAll(){
        List<Student> res = studentService.getAll();

        return new ResponseEntity<>(res, HttpStatus.OK);
    }


    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Student student){
        
        studentService.addStudent(student);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        studentService.deleteStudent(id);

        return new ResponseEntity<>(HttpStatus.OK);
    } 


    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestParam(required = false) String name, @RequestParam(required = false) String email){

        studentService.updateStudent(id,name,email);

        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
