package com.banktalib.notificationservice.NotificationsService.Consumer;

import com.banktalib.UserClient.TransactionDto;
import com.banktalib.notificationservice.NotificationsService.Dto.NotificationDto;
import com.banktalib.notificationservice.NotificationsService.Service.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {

    @Autowired
    private INotificationService notificationService;

    @KafkaListener(topics = "Notifications-events", groupId = "notifications", containerFactory = "kafkaListenerContainerFactory")
    public void consume(TransactionDto transactionDto) {

//        if (transactionDto.getTypeTransaction().equals("DEPOSIT")) {
            NotificationDto notificationDto = new NotificationDto();
            notificationDto.setNotificationTitle("Deposit");
            notificationDto.setAccountNumber(transactionDto.getSenderAccountNumber());
            notificationDto.setMessage("You have deposited " + transactionDto.getAmount() + " to your account");
            notificationService.createNotification(notificationDto);
//        }
//        else if (transactionDto.getTypeTransaction()=="WITHDRAW") {
//            NotificationDto notificationDto = new NotificationDto();
//            notificationDto.setAccountNumber(transactionDto.getAccountNumber());
//            notificationDto.setMessage("You have withdrawn " + transactionDto.getAmount() + " from your account");
//            notificationService.createNotification(notificationDto);
//        }
//        else if (transactionDto.getTypeTransaction()=="TRANSFER") {
//            NotificationDto notificationDto = new NotificationDto();
//            notificationDto.setAccountNumber(transactionDto.getAccountNumber());
//            notificationDto.setMessage("You have transferred " + transactionDto.getAmount() + " from your account to " + transactionDto.getReceiverAccountNumber());
//            notificationService.createNotification(notificationDto);
//        }
//        else {
//            NotificationDto notificationDto = new NotificationDto();
//            notificationDto.setAccountNumber(transactionDto.getAccountNumber());
//            notificationDto.setMessage("You have paid " + transactionDto.getAmount() + " to " + transactionDto.getReceiverAccountNumber());
//            notificationService.createNotification(notificationDto);
//        }


    }
}