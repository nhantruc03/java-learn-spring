package com.example.demo.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Student;
import com.example.demo.repo.StudentRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    private final Logger logger = LoggerFactory.getLogger(StudentService.class);


    @Async
    public CompletableFuture<List<Student>> saveUsers(MultipartFile file) throws Exception {
        long start = System.currentTimeMillis();
        List<Student> users = parseCSVFile(file);
        logger.info("saving list of users of size {}", users.size(), "" + Thread.currentThread().getName());
        users = studentRepository.saveAll(users);
        long end = System.currentTimeMillis();
        logger.info("Total time {}", (end - start));
        return CompletableFuture.completedFuture(users);
    }
    @Async
    public CompletableFuture<List<Student>> findAllUsers(){
        logger.info("get list of user by "+Thread.currentThread().getName());
        List<Student> users=studentRepository.findAll();
        return CompletableFuture.completedFuture(users);
    }
    

    private List<Student> parseCSVFile(final MultipartFile file) throws Exception {
        final List<Student> users = new ArrayList<>();
        try {
            try (final BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                String line;
                while ((line = br.readLine()) != null) {
                    final String[] data = line.split(",");
                    final Student user = new Student();
                    user.setName(data[0]);
                    user.setEmail(data[1]);
                    user.setGender(data[2]);
                    users.add(user);
                }
                return users;
            }
        } catch (final IOException e) {
            logger.error("Failed to parse CSV file {}", e);
            throw new Exception("Failed to parse CSV file {}", e);
        }
    }



}
