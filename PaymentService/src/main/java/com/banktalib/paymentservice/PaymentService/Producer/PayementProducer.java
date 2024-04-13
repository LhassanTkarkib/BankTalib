package com.banktalib.paymentservice.PaymentService.Producer;

import com.banktalib.UserClient.TransactionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class PayementProducer {

    @Autowired
    private KafkaTemplate<String, TransactionDto> kafkaTemplate;

    public void sendMessage(TransactionDto event){

        Message<TransactionDto> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, "Notifications-events")
                .build();
        kafkaTemplate.send(message);
    }   
}