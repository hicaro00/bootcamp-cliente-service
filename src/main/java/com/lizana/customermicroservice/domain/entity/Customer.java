package com.lizana.customermicroservice.domain.entity;

import com.lizana.customermicroservice.domain.dto.AddressDto;
import com.lizana.customermicroservice.domain.dto.BankacountsList;
import com.lizana.customermicroservice.domain.dto.EmergencyContactDto;
import com.lizana.customermicroservice.domain.dto.ExistingCredits;
import com.lizana.customermicroservice.domain.objetos.ClientStatus;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


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
    private String clientType; //tipo de cliente  personal o empresarial
    private ClientStatus clientStatus; //firmante || titular
    private String profile; // vip || normal
    private String address; //direccion principal
    private String emailAddress; //correo electronico
    private String phoneNumber; //numoeer de telefono
    private String dateOfBirth; //fecha de nacimiento
    private List<BankacountsList> bankAccounts = new ArrayList<>(); //lista de cuetnas bancarias asociadas al cliente
    private List<String> creditCards = new ArrayList<>(); // lista de tarjetas de credito asociadas al clietne
    private List<ExistingCredits> loans = new ArrayList<>(); // lista de creditos asociados al cliente
    private List<AddressDto> addresses = new ArrayList<>(); //lista de direcciones asociadas al cliente
    private List<EmergencyContactDto> emergencyContacts = new ArrayList<>(); //lista de contactos de emergencia del cliente



}
