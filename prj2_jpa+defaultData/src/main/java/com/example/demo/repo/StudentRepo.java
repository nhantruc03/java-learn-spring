package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {
    

    @Query("SELECT s FROM Student s Where s.email = ?1")
    Optional<Student> findStudentByEmail(String email);

}
