package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.MessageDTO;

public interface MessageRepository extends JpaRepository<MessageDTO , Integer>{

    List<MessageDTO> findByStatus(boolean status);
    
}
