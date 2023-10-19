package com.lizana.customermicroservice.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import java.math.BigDecimal;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class BankAccount {

        @Id
        private String accountId;
        private String customerId;
        private String accountType;//ahorro libre /cuenta corriente/plazo fijo
        private BigDecimal balance;



}
