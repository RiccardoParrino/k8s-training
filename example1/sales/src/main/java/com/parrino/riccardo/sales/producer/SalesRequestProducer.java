package com.parrino.riccardo.sales.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.parrino.riccardo.model.sales.SalesRequest;

@Service
public class SalesRequestProducer {

    @Autowired
    private KafkaTemplate<String, SalesRequest> kafkaTemplate;
    
    public void salesRequestSender(String topic, SalesRequest message) {
        kafkaTemplate.send(topic, message);
        System.out.println("Sent a sales request to topic: " + topic);
    }

}
