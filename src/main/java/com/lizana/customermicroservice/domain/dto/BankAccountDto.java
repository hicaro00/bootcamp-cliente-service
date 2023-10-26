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


    private String id;//id del documento customer en mongo db
    private String accountType;//Ahorro||Cuenta Corriente|| plazo fijo
    private BigDecimal balance; //saldo de la cuenta
    private String documentType; //dni||pasaporte||ruc
    private String identificationNumber; //numero del ruc o dni
    private String firstName; //nombre
    private String lastName; //apellido
    private String address;  //direccion



}
