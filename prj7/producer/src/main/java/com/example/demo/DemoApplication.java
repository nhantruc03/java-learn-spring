package com.example.demo;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.constant.Topic;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	NewTopic notification(){
		return new NewTopic(Topic.NOTIFICATION.name(), 2, (short) 1);
	}

	@Bean
	NewTopic statistic(){
		return new NewTopic(Topic.STATISTIC.name(), 2, (short)1);
	}

}
