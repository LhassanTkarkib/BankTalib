package com.banktalib.notificationservice.NotificationsService.Mapper;

import com.banktalib.notificationservice.NotificationsService.Dto.NotificationDto;
import com.banktalib.notificationservice.NotificationsService.Entity.NotificationEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface NotificationMapper {
    NotificationEntity toEntity(NotificationDto notificationDto);

    NotificationDto toDto(NotificationEntity notificationEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    NotificationEntity partialUpdate(NotificationDto notificationDto, @MappingTarget NotificationEntity notificationEntity);
}