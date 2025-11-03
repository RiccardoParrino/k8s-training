package com.parrino.riccardo.checkout.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.parrino.riccardo.model.production.ProductionRequest;

@Service
public class ProductionRequestProducer {
    
    @Autowired
    private KafkaTemplate<String, ProductionRequest> kafkaTemplate;

    public void sendProductionRequest(String topic, ProductionRequest productionRequest) {
        kafkaTemplate.send(topic, productionRequest);
        System.out.println("Sent a production request to serve an order");
    }

}
