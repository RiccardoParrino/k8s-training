package com.parrino.riccardo.checkout.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.parrino.riccardo.model.SalesRequest;

@Service
public class SalesRequestConsumer {
    
    @KafkaListener(topics = "sales-requests-topic", groupId="checkout")
    public void salesRequestConsumer(SalesRequest salesRequest) {
        System.out.println("Send blocking confirmation request to frontend checkout...");

        System.out.println("Once obtained the confirmation, send production request and management requests");
        
        
    }
    
}
