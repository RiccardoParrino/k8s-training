package com.parrino.riccardo.production.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.parrino.riccardo.model.production.ProductionResponse;

@Service
public class ProductionRequestProducer {
    
    @Autowired
    private KafkaTemplate<String, ProductionResponse> kafkaTemplate;

    public void productionResponseSender(String topic, ProductionResponse productionResponse) {
        kafkaTemplate.send(topic, productionResponse);
        System.out.println("production id is in another state");
    }
    
}
