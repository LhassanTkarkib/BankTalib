package com.banktalib.users.usersmicroservice.ServiceUser.Mapper;

import com.banktalib.users.usersmicroservice.ServiceUser.Dto.AccountDto;
import com.banktalib.users.usersmicroservice.ServiceUser.Entity.AccountEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {com.banktalib.users.usersmicroservice.ServiceUser.Mapper.UserMapper.class})
public interface AccountMapper {
    com.banktalib.users.usersmicroservice.ServiceUser.Entity.AccountEntity toEntity(AccountDto accountDto);

    AccountDto toDto(com.banktalib.users.usersmicroservice.ServiceUser.Entity.AccountEntity accountEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    AccountEntity partialUpdate(AccountDto accountDto, @MappingTarget AccountEntity accountEntity);
}