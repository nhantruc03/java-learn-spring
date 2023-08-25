package com.example.demo.service;

import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.example.demo.entity.MessageDTO;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
 
@Service
public class EmailServiceImpl {
    
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private SpringTemplateEngine springTemplateEngine;

    @Value("${spring.mail.username}")
    private String from;

    @Async
    public void sendMail(MessageDTO messageDTO) {

        try {
            logger.info("START ... sending mail");

            MimeMessage message = emailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, StandardCharsets.UTF_8.name());

            Context context = new Context();
            context.setVariable("name", messageDTO.getToName());
            context.setVariable("content", messageDTO.getContent());
            String html = springTemplateEngine.process("welcome-email", context);

            // Send mail
            helper.setFrom(from);
            helper.setTo(messageDTO.getTo());
            helper.setSubject(messageDTO.getSubject());
            helper.setText(html,true);

            emailSender.send(message);

            logger.info("END ... sent success");
        } catch (MessagingException e) {
            logger.info("Email send with error: ",e.getMessage());
        }

    }

}
