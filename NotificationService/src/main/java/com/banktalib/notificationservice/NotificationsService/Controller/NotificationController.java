package com.banktalib.notificationservice.NotificationsService.Controller;


import com.banktalib.notificationservice.NotificationsService.Dto.NotificationDto;
import com.banktalib.notificationservice.NotificationsService.Service.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Notification")
public class NotificationController {

    @Autowired
    private INotificationService notificationService;

    @GetMapping("/getAllNotifications")
    public ResponseEntity<List<NotificationDto>> getAllNotifications() {
        return new ResponseEntity<>(notificationService.getAllNotifications(), HttpStatus.OK);
    }

    @GetMapping("/getNotificationByAccountNumber/{accountNumber}")
    public ResponseEntity<List<NotificationDto>> getNotificationByAccountNumber(@PathVariable String accountNumber) {
        return new ResponseEntity<>(notificationService.getNotificationByAccountNumber(accountNumber), HttpStatus.OK);
    }

    @GetMapping("/getNotificationById/{id}")
    public ResponseEntity<NotificationDto> getNotificationById(@PathVariable Long id) {
        return new ResponseEntity<>(notificationService.getNotificationById(id), HttpStatus.OK);
    }

    @PostMapping("/createNotification")
    public ResponseEntity<NotificationDto> createNotification(@RequestBody NotificationDto notificationDto) {
        return new ResponseEntity<>(notificationService.createNotification(notificationDto), HttpStatus.CREATED);
    }

    @PutMapping("/updateNotification")
    public ResponseEntity<NotificationDto> updateNotification(@RequestBody NotificationDto notificationDto) {
        return new ResponseEntity<>(notificationService.updateNotification(notificationDto), HttpStatus.OK);
    }

    @DeleteMapping("/deleteAllNotifications")
    public ResponseEntity<String> deleteAllNotifications() {
        notificationService.deleteAllNotifications();
        return new ResponseEntity<>("All notifications deleted successfully", HttpStatus.OK);
    }

}
