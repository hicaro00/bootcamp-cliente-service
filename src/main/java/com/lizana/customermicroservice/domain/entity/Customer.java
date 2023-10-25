package com.lizana.customermicroservice.domain.entity;

import com.lizana.customermicroservice.domain.dto.AddressDto;
import com.lizana.customermicroservice.domain.dto.BankAccountDto;
import com.lizana.customermicroservice.domain.dto.EmergencyContactDto;
import com.lizana.customermicroservice.domain.objetos.ClientStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "customer")
public class Customer {
    @Id
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
