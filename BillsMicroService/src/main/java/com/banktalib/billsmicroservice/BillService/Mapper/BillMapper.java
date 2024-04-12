package com.banktalib.billsmicroservice.BillService.Mapper;

import com.banktalib.billsmicroservice.BillService.Dto.BillDto;
import com.banktalib.billsmicroservice.BillService.Entity.BillEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface BillMapper {
    BillEntity toEntity(BillDto billDto);

    BillDto toDto(BillEntity billEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    BillEntity partialUpdate(BillDto billDto, @MappingTarget BillEntity billEntity);
}