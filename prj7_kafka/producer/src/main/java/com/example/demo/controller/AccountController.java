package com.example.demo.controller;

import java.util.Date;

import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.constant.Topic;
import com.example.demo.entity.AccountDTO;
import com.example.demo.entity.MessageDTO;
import com.example.demo.entity.StatisticDTO;
import com.example.demo.repo.AccountRepository;
import com.example.demo.repo.MessageRepository;
import com.example.demo.repo.StatisticRepositoy;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/account")
@AllArgsConstructor
public class AccountController {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    private final AccountRepository accountRepository;
    private final MessageRepository messageRepository;
    private final StatisticRepositoy statisticRepositoy;

    @PostMapping("/new")
    public ResponseEntity<AccountDTO> create(@RequestBody AccountDTO accountDTO) {

        StatisticDTO statisticDTO = StatisticDTO.builder().message(String.format("Account %s", accountDTO.getEmail()))
                .createdDate(new Date()).status(false).build();

        MessageDTO messageDTO = MessageDTO.builder()
                .to(accountDTO.getEmail())
                .toName(accountDTO.getName())
                .subject("Subject of the Message!")
                .content("Content of the message!!")
                .status(false)
                .build();

        accountRepository.save(accountDTO);
        messageRepository.save(messageDTO);
        statisticRepositoy.save(statisticDTO);

        // kafkaTemplate.send(Topic.NOTIFICATION.name(), messageDTO).whenComplete((result, ex) -> {
        //     if (ex == null) {
        //         System.out.println("Sent message=[" + accountDTO.getEmail() +
        //                 "] with offset=[" + result.getRecordMetadata().offset() + "]");
        //     } else {
        //         System.out.println("Unable to send message=[" +
        //                 accountDTO.getEmail() + "] due to : " + ex.getMessage());
        //     }
        // });

        // kafkaTemplate.send(Topic.NOTIFICATION.name(), messageDTO);
        kafkaTemplate.send(Topic.STATISTIC.name(), statisticDTO);

        return ResponseEntity.ok(accountDTO);
    }

}
