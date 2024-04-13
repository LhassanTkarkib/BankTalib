package com.banktalib.notificationservice.NotificationsService.Repository;

import com.banktalib.notificationservice.NotificationsService.Dto.NotificationDto;
import com.banktalib.notificationservice.NotificationsService.Entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {

    List<NotificationEntity> findAllByAccountNumber(String accountNumber);
}
