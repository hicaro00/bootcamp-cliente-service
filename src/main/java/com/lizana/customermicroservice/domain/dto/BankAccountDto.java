package com.lizana.customermicroservice.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankAccountDto {

    private String customerId;
    private String accountType;
    private BigDecimal balance;
}
