package com.banktalib.notificationservice.NotificationsService.Service.Implimenetation;


import com.banktalib.notificationservice.NotificationsService.Dto.NotificationDto;
import com.banktalib.notificationservice.NotificationsService.Mapper.NotificationMapper;
import com.banktalib.notificationservice.NotificationsService.Repository.NotificationRepository;
import com.banktalib.notificationservice.NotificationsService.Service.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationService implements INotificationService {

    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private NotificationMapper notificationMapper;

    @Override
    public List<NotificationDto> getAllNotifications() {
        return notificationRepository.findAll().stream().map(notificationMapper::toDto).collect(Collectors.toList());

    }



    @Override
    public NotificationDto getNotificationById(Long id) {
        return notificationMapper.toDto(notificationRepository.findById(id).orElseThrow());
    }

    @Override
    public NotificationDto createNotification(NotificationDto notificationDto) {
        return notificationMapper.toDto(notificationRepository.save(notificationMapper.toEntity(notificationDto)));
    }

    @Override
    public NotificationDto updateNotification(NotificationDto notificationDto) {
        return notificationMapper.toDto(notificationRepository.save(notificationMapper.toEntity(notificationDto)));
    }

    @Override
    public void deleteAllNotifications() {
        notificationRepository.deleteAll();
    }

}


