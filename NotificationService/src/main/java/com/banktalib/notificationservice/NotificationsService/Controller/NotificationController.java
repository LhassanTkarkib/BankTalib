package com.banktalib.notificationservice.NotificationsService.Controller;


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


}
