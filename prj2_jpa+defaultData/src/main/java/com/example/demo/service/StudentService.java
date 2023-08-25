package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Student;
import com.example.demo.repo.StudentRepo;

import jakarta.transaction.Transactional;

@Service
public class StudentService {
    
    private final StudentRepo studentRepo;
    

    @Autowired
    public StudentService(StudentRepo studentRepo){
        this.studentRepo = studentRepo;
    }

    public List<Student> getAll(){
        return studentRepo.findAll();
    }

    public void addStudent(Student student){
        Optional<Student> findStudentByEmail = studentRepo.findStudentByEmail(student.getEmail());

        if(findStudentByEmail.isPresent()){
            throw new IllegalStateException("Email taken");
        }

        studentRepo.save(student);
    }

    public void deleteStudent(Long id){

        boolean exist = studentRepo.existsById(id);


        if(!exist){
            throw new IllegalStateException("Student not existed! ID: "+ id);
        }

        studentRepo.deleteById(id);
    }

    @Transactional
    public void updateStudent(Long id,String name, String email){
        Student student = studentRepo.findById(id).orElseThrow(()-> new IllegalStateException("Student with id " + id + " does not exist!"));

        if(name != null && !name.isEmpty() && !student.getName().equals(name)){
            student.setName(name);
        }

        if(email != null && !email.isEmpty() && !student.getEmail().equals(email)){
            Optional<Student> temp = studentRepo.findStudentByEmail(email);
            if(temp.isPresent()){
                throw new IllegalStateException("Email taken: " + email);
            }
            student.setEmail(email);
        }


    }



    
}
