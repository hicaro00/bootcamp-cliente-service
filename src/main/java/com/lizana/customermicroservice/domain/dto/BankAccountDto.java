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



    private String id;//id del coumento en mongo db
    private String accountNumber;//numero unico de la cuenta
    private AccountType accountType;//Ahorro||Cuenta Corriente|| plazo fijo
    private BigDecimal balance;//saldo de la cuenta


    public void setAccountType(AccountType accountType) {
        if (accountType == AccountType.SAVINGS
                || accountType == AccountType.CURRENTACCOUNT
                || accountType == AccountType.FIXEDTERM) {
            this.accountType = accountType;
        } else {
            throw new CustomException("El valor de accountType no es válido. Debe ser PERSONAL|BUSINESS.");
        }
    }

}
