package com.example.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.demo.entity.MessageDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final EmailServiceImpl emailServiceImpl;

    @KafkaListener(id = "notificationGroup", topics = "NOTIFICATION")
    public void listen(MessageDTO messageDTO){
        logger.info("Receiver: ", messageDTO.getTo());
        emailServiceImpl.sendMail(messageDTO);
    }

    
}
