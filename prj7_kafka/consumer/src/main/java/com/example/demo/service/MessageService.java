package com.example.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;

import com.example.demo.entity.MessageDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());



    @RetryableTopic(attempts = "5", dltTopicSuffix = ".DLT", backoff = @Backoff(delay = 2000, multiplier = 2))
    @KafkaListener(id = "notificationGroup", topics = "NOTIFICATION")
    public void listen(MessageDTO messageDTO){
        logger.info("Receiver: " + messageDTO.getTo());
        // emailServiceImpl.sendMail(messageDTO);

        throw new RuntimeException();
    }

    @KafkaListener(id = "dltGroup", topics = "NOTIFICATION.DLT")
    public void dltListen(MessageDTO messageDTO){
        logger.info("Receive dlt message" + messageDTO.getTo());
    }

    
}
