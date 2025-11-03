package com.parrino.riccardo.checkout.consumer;

import org.springframework.kafka.annotation.KafkaListener;

import com.parrino.riccardo.model.production.ProductionResponse;

public class ProductionResponseConsumer {
    
    @KafkaListener(topics = "production-response-topic", groupId = "checkout")
    public void productionResponseTopic(ProductionResponse productionResponse) {
        System.out.println(productionResponse + "is in state: working or finished!");
    }

}
