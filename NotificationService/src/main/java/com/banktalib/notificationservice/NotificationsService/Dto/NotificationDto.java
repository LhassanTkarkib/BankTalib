package com.banktalib.notificationservice.NotificationsService.Dto;

import com.banktalib.notificationservice.NotificationsService.Enums.NotificationCetgory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.banktalib.notificationservice.NotificationsService.Entity.NotificationEntity}
 */
@AllArgsConstructor
@Getter
@Data
@NoArgsConstructor
public class NotificationDto implements Serializable {
    private Long idNotification;
    private String accountNumber;
    private String notificationTitle;
    private String message;
    private java.util.Date date;
    private NotificationCetgory notificationCetgory;
}