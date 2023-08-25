package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.converter.JsonMessageConverter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.util.backoff.FixedBackOff;

@SpringBootApplication
@EnableAsync
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public JsonMessageConverter converter(){
		return new JsonMessageConverter();
	}

	// @Bean
	// public DefaultErrorHandler errorHandler(KafkaOperations<String, Object> tempalte){
	// 	return new DefaultErrorHandler(new DeadLetterPublishingRecoverer(tempalte), new FixedBackOff(1000L, 2));

	// }

}
