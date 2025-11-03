package com.parrino.riccardo.checkout.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.parrino.riccardo.model.transaction.TransactionRequest;

@Service
public class ManagementRequestProducer {
    
    @Autowired
    private KafkaTemplate<String, TransactionRequest> kafkaTemplate;

    public void sendTransactionRrequest(String topic, TransactionRequest transactionRequest) {
        kafkaTemplate.send(topic, transactionRequest);
        System.out.println("Send transaction request to register an order");
    }

}
