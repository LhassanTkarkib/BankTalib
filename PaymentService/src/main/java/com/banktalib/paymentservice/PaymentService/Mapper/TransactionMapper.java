package com.banktalib.paymentservice.PaymentService.Mapper;

import com.banktalib.paymentservice.PaymentService.Dto.TransactionDto;
import com.banktalib.paymentservice.PaymentService.Entity.TransactionEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface TransactionMapper {
    TransactionEntity toEntity(TransactionDto transactionDto);

    TransactionDto toDto(TransactionEntity transactionEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(@MappingTarget TransactionEntity transactionEntity, TransactionDto transactionDto);
}