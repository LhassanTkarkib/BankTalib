package com.banktalib.gateway.config.Mapper;

import com.banktalib.gateway.config.DTO.UserRepresentationDto;
import org.keycloak.representations.idm.UserRepresentation;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserRepresentationMapper {
    UserRepresentation toEntity(UserRepresentationDto userRepresentationDto);

    UserRepresentationDto toDto(UserRepresentation userRepresentation);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    UserRepresentation partialUpdate(UserRepresentationDto userRepresentationDto, @MappingTarget UserRepresentation userRepresentation);
}