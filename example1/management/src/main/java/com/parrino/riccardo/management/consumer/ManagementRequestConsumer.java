package com.parrino.riccardo.management.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.parrino.riccardo.model.transaction.TransactionRequest;

@Service
public class ManagementRequestConsumer {
    
    @KafkaListener(topics = "management-request-consumer", groupId = "management")
    public void managementRequestConsumer(TransactionRequest transactionRequest) {
        System.out.println("Save transaction to db for subsequent analysis");
    }

}
