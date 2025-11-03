package com.parrino.riccardo.production.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.parrino.riccardo.model.production.ProductionRequest;

@Service
public class ProductionRequestConsumer {
    
    @KafkaListener
    public void productionRequestHandler(ProductionRequest productionRequest) {
        System.out.println("Signaling to frontend another request");
        System.out.println("Saving in in-memory db (like a simple list) another request");
    }

}
