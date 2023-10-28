package com.lizana.customermicroservice.domain.dto;

import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepositAmountDto {


        private String depositoId;
        private BigDecimal amount;
        private Date depositDate;

}
