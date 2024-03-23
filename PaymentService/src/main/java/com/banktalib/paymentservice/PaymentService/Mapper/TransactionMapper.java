package com.banktalib.paymentservice.PaymentService.Mapper;

import com.banktalib.paymentservice.PaymentService.Dto.TransactionDto;
import com.banktalib.paymentservice.PaymentService.Entity.TransactionEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TransactionMapper {
    TransactionEntity toEntity(TransactionDto transactionDto);

    TransactionDto toDto(TransactionEntity transactionEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TransactionEntity partialUpdate(TransactionDto transactionDto, @MappingTarget TransactionEntity transactionEntity);
}