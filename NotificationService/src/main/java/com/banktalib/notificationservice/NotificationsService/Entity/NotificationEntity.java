package com.banktalib.notificationservice.NotificationsService.Entity;
import com.banktalib.notificationservice.NotificationsService.Enums.NotificationCetgory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "NotificationEntity")
public class NotificationEntity {
    @Id
    @SequenceGenerator(
            name = "Notification_sequence",
            sequenceName = "Notification_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Notification_sequence")
    private Long idNotification;

    @Column(name= "accountNumber")
    private String accountNumber;

    @Column(name= "notificationTitle")
    private String notificationTitle;

    @Column(name = "message")
    private String message;

    @Column(name = "date")
    private Date date;

    @Column(name = "notificationCetgory")
    @Enumerated(EnumType.STRING)
    private NotificationCetgory notificationCetgory;

}
