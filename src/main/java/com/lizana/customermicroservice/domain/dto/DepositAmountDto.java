package com.lizana.customermicroservice.domain.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepositAmountDto {


        private String movementId;
        private BigDecimal amount;
        private String originMovement;
        private LocalDate dateOfMovement;
        private String authorizationCode;

}
