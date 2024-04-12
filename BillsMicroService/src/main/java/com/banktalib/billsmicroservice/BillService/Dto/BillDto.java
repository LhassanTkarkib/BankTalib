package com.banktalib.billsmicroservice.BillService.Dto;

import com.banktalib.billsmicroservice.BillService.Enums.PayementStatus;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.banktalib.billsmicroservice.BillService.Entity.BillEntity}
 */
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BillDto implements Serializable {
    private Long idBill;
    private String billName;
    private Double amount;
    private Date dueDate;
    private PayementStatus payementStatus;
    private String accountNumberInitiated;
    private String payersAccountNumber;
}