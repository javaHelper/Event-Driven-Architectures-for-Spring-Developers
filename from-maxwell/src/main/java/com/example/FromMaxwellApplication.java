package com.example;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

@SpringBootApplication
public class FromMaxwellApplication {

	public static void main(String[] args) {
		SpringApplication.run(FromMaxwellApplication.class, args);
	}

	@Bean
	public NewTopic topic() {
		return TopicBuilder.name("currency").build();
	}

	@Bean
	public NewTopic maxwell() {
		return TopicBuilder.name("maxwell").compact().build();
	}

	@Bean
	public RecordMessageConverter converter() {
		return new StringJsonMessageConverter();
	}
}
