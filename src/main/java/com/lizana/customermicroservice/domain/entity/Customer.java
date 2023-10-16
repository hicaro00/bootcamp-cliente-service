package com.lizana.customermicroservice.domain.entity;

import com.lizana.customermicroservice.domain.objetos.ClientType;
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
    private String id;
    private String dni;
    private String ruc;
    private String name;
    private String clientType; //tipo de cliente  personal o empresarial
}
