package com.banktalib.notificationservice.NotificationsService.Consumer;

import com.banktalib.UserClient.TransactionDto;
import com.banktalib.notificationservice.NotificationsService.Service.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {

    @Autowired
    private INotificationService notificationService;

    @KafkaListener(topics = "Notifications-events", groupId = "notifications", containerFactory = "kafkaListenerContainerFactory")
//    public void consume(PostDto postDto) {
//       GroupeDto group = groupeService.getGroupeById(postDto.getGroupeId());
//       groupeService.deleteGroupe(postDto.getGroupeId(),group.getAdminId());
//       System.out.println("Consumed message: upp" + postDto);
//    }

    public void consume(TransactionDto transactionDto) {



    }
}