package com.parrino.riccardo.sales.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.parrino.riccardo.model.SalesRequest;

@Service
public class SalesRequestConsumer {
    
    @KafkaListener(topics = "sales-requests-topic", groupId="checkout")
    public void salesRequestConsumer(SalesResponse salesRequest) {
        
        
    }
    
}
