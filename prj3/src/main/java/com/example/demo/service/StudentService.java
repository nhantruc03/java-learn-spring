package com.example.demo.service;

import java.util.List;

import org.aspectj.internal.lang.annotation.ajcDeclareAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.NotFoundStudentException;
import com.example.demo.model.Student;
import com.example.demo.repo.StudentRepo;

@Service
public class StudentService {
    
    @Autowired
    private StudentRepo repo;

    public List<Student> findAll(){
        return repo.findAll();
    }

    public Student addStudent(Student student){
        return repo.save(student);
    }

    public Student removeById(Long id){

        Student existStudent = repo.findById(id).orElseThrow(() -> new NotFoundStudentException("Student with id " + id +" is not exist!"));

        repo.deleteById(id);

        return existStudent;
    }
}
