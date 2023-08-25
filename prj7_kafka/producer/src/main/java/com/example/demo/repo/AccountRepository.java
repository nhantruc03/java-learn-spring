package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.AccountDTO;

public interface AccountRepository extends JpaRepository<AccountDTO, Integer>{
    
}
