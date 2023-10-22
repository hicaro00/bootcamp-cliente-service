package com.lizana.customermicroservice.domain.dto;

import com.lizana.customermicroservice.domain.objetos.ClientStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    private String id;
    private String dni;
    private String ruc;
    private String name;
    private String clientType;
    private ClientStatus clientStatus;
}
