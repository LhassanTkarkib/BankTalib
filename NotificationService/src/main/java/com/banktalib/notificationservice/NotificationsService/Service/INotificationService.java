package com.banktalib.notificationservice.NotificationsService.Service;



import com.banktalib.notificationservice.NotificationsService.Dto.NotificationDto;

import java.util.List;

public interface INotificationService {


    List<NotificationDto> getAllNotifications();

    NotificationDto getNotificationById(Long id);

    NotificationDto createNotification(NotificationDto notificationDto);

    NotificationDto updateNotification(NotificationDto notificationDto);

    void deleteAllNotifications();
}