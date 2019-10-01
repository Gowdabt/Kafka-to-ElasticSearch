package com.infinera.dna.listener;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infinera.dna.model.NotificationResource;
import com.infinera.dna.model.PerformanceManagement;
import com.infinera.dna.model.PerformanceResource;
import com.infinera.dna.model.PmMeasurement;
import com.infinera.dna.model.Resource;
import com.infinera.dna.repository.PerformanceMgmtRepository;
import com.infinera.dna.repository.ResourceRepository;

/**
 * @author Harshith Gowda B T
 *
 */
@Service
public class KafkaConsumer {

	@Autowired
	private ResourceRepository resRepo;


	/**
	 * 
	 * Reads new message from Kafka Broker
	 */

	//if you have multiple topics to consume data you can specify those topic by splitting(mention those topic names as , comma seperated in application.properties)
	@KafkaListener(topics = "#{'${kafka.topic}'.split(',')}", groupId = "SampleConsumer_mess")
	public void consume(ConsumerRecord<?, Map<String, String>> message) {

		try {
			String topicName = message.topic();
			String messageData = String.valueOf(message.value());
			ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
			
			//map consumed message to particular bean
				NotificationResource nr = mapper.readValue(messageData, NotificationResource.class));
				resRepo.save(nr);
			

		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
