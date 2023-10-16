package com.lizana.customermicroservice.domain.dto;

import com.lizana.customermicroservice.domain.objetos.ClientType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    private String id;
    private String dni;
    private String ruc;
    private String name;
    private String clientType;
}
