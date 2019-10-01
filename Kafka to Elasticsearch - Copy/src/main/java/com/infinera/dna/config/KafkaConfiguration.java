package com.infinera.dna.config;

/**
 * @author Harshith Gowda B T
 *
 */

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

@EnableKafka
@Configuration
public class KafkaConfiguration {

	@Value("${kafka.host}")
	private String kafkaHost;

	@Value("${kafka.port}")
	private String kafkaPort;

	@Value("${kafka.message.groupid}")
	private String kafkaMessageGroupid;
	
	
	@Bean
	public ConsumerFactory<Integer, String> consumerFactory() {
		Map<String, Object> config = new HashMap<>();

		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaHost + ":" + kafkaPort);
		config.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaMessageGroupid);
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
				"org.apache.kafka.common.serialization.IntegerDeserializer");
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
				"org.apache.kafka.common.serialization.StringDeserializer");
		return new DefaultKafkaConsumerFactory<>(config);
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<Integer, String> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<Integer, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}

}
