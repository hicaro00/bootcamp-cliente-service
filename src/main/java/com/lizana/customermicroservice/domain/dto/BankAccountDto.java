package com.lizana.customermicroservice.domain.dto;

import com.lizana.customermicroservice.application.customexeption.CustomException;
import com.lizana.customermicroservice.domain.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankAccountDto {

    private String customerId;
    private AccountType accountType;
    private BigDecimal balance;


    public void setAccountType(AccountType accountType) {
        if (accountType == AccountType.SAVINGS || accountType == AccountType.CURRENT_ACCOUNT|| accountType == AccountType.FIXED_TERM) {
            this.accountType = accountType;
        } else {
            throw new CustomException("El valor de accountType no es válido. Debe ser SAVINGS, CURRENT_ACCOUNT o FIXED_TERM.");
        }
    }

}
