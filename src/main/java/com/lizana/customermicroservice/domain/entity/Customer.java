package com.lizana.customermicroservice.domain.entity;

import com.lizana.customermicroservice.domain.dto.AddressDto;
import com.lizana.customermicroservice.domain.dto.BankAccountDto;
import com.lizana.customermicroservice.domain.dto.EmergencyContactDto;
import com.lizana.customermicroservice.domain.enums.ClientType;
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
    private String id; //identificAdor en el mongodb
    private String firstName; //nombre
    private String lastName;    // apellido
    private String documentType;//dni||pasaporte||ruc
    private String identificationNumber; //numero correspondiente al documentType
    private ClientType clientType; //tipo de cliente  personal o empresarial
    private ClientStatus clientStatus; //firmante || titular
    private String profile; // vip || normal
    private String address; //direccion principal
    private String emailAddress; //correo electronico
    private String phoneNumber; //numoeer de telefono
    private String dateOfBirth; //fecha de nacimiento
    private List<BankAccountDto> bankAccounts = new ArrayList<>(); //lista de cuetnas bancarias asociadas al cliente
    private List<String> creditCards = new ArrayList<>(); // lista de tarjetas de credito asociadas al clietne
    private List<String> loans = new ArrayList<>(); // lista de creditos asociados al cliente
    private List<AddressDto> addresses = new ArrayList<>(); //lista de direcciones asociadas al cliente
    private List<EmergencyContactDto> emergencyContacts = new ArrayList<>(); //lista de contactos de emergencia del cliente
}
