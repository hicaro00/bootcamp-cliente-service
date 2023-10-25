package com.lizana.customermicroservice.domain.dto;

import com.lizana.customermicroservice.domain.objetos.ClientStatus;
import com.lizana.customermicroservice.domain.objetos.ClientType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    private String id;
    private String firstName;
    private String lastName;
    private String documentType;//dni//pasaporte
    private String ruc;
    private String identificationNumber;
    private String clientType; //tipo de cliente  personal o empresarial
    private ClientStatus clientStatus;
    private String address;
    private String emailAddress;
    private String phoneNumber;
    private String dateOfBirth;
    private List<BankAccountDto> bankAccounts = new ArrayList<>();
    private List<String> creditCards = new ArrayList<>();
    private List<String> loans = new ArrayList<>();
    private List<AddressDto> addresses = new ArrayList<>();
    private List<EmergencyContactDto> emergencyContacts = new ArrayList<>();
}
