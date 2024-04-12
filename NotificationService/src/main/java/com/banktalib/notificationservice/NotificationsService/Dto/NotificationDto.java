package com.banktalib.notificationservice.NotificationsService.Dto;

import com.banktalib.notificationservice.NotificationsService.Enums.NotificationCetgory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.banktalib.notificationservice.NotificationsService.Entity.NotificationEntity}
 */
@AllArgsConstructor
@Getter
@Data
public class NotificationDto implements Serializable {
    private Long idNotification;
    private String AccountNumber;
    private String NotificationTitle;
    private String Message;
    private java.util.Date Date;
    private NotificationCetgory notificationCetgory;
}