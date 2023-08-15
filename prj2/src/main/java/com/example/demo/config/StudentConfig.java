package com.example.demo.config;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.model.Student;
import com.example.demo.repo.StudentRepo;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepo studentRepo){
        return args ->{
            Student  a = new Student(1L, "nhan", "nhantruc03@gmail.com", LocalDate.of(1999, Month.NOVEMBER, 15));
          
            Student  b = new Student(2L, "test", "test@gmail.com", LocalDate.of(1999, Month.NOVEMBER, 15));

            studentRepo.saveAll(
                List.of(a,b)
            );
        };
    }

}
