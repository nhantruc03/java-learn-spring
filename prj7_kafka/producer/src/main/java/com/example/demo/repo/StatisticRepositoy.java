package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.StatisticDTO;

public interface StatisticRepositoy extends JpaRepository<StatisticDTO, Integer>{
    List<StatisticDTO> findByStatus(boolean status);

}
