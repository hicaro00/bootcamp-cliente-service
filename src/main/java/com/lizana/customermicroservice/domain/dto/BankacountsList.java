package com.lizana.customermicroservice.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.kafka.common.protocol.types.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankacountsList {


    private String accountNumber; //Ahorro||Cuenta Corriente|| plazo fijo
    private String balance;  //bance general de la cuenta
    private String accountType; // tipóde cuenta AHORRO||CUETNA CORREITNE||PLAZO FIJO
    private String accountId; //id del la cuenta creada asociada al usuario


}
