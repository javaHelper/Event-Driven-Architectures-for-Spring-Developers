package com.example.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

import com.example.model.ChangeEvent;
import com.example.model.ChangeEvent.Type;

@Configuration
public class KafkaConfig {
	private static final Logger logger = LoggerFactory.getLogger(KafkaConfig.class);

	@Autowired
	private KafkaTemplate<String, Double> template;

	@KafkaListener(id = "cdc", topics = "maxwell")
	public void listen(ChangeEvent change) {
		logger.info("Received >>> " + change);
		
		if (change.getTable().equals("currency")) {
			Type type = change.getType();
			if (type.equals(Type.insert) || type.equals(Type.update)) {
				String symbol = change.getData().getSymbol();
				double rate = ((change.getData().getRate())) / 1000.;
				this.template.send("currency", symbol, rate);
			}
			if (type.equals(Type.delete)) {
				String symbol = change.getData().getSymbol();
				this.template.send("currency", symbol, null);
			}
		}
	}
}