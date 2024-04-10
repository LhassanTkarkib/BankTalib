package com.banktalib.billsmicroservice.BillService.Dto;

import com.banktalib.billsmicroservice.BillService.Enums.PayementStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.banktalib.billsmicroservice.BillService.Entity.BillEntity}
 */
@AllArgsConstructor
@Getter
public class BillDto implements Serializable {
    private final Long idBill;
    private final Double amount;
    private final Date dueDate;
    private final PayementStatus payementStatus;
    private final String accountNumberInitiated;
    private final String accountNumberInvolved;
}