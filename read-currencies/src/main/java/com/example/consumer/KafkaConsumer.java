package com.example.consumer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

@Configuration
public class KafkaConsumer {
	private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

	private final Map<String, Double> currencies = new ConcurrentHashMap<>();

	@KafkaListener(id = "currency1", topics = "currency")
	public void listen(@Payload(required = false) Double rate, 
					   @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key) {
		
		logger.info("=== Rate: {}, Key{}", rate, key);
		if (rate == null) {
			this.currencies.remove(key);
		} else {
			this.currencies.put(key, rate);
		}
		logger.info("Currencies now: " + this.currencies);
	}
}
