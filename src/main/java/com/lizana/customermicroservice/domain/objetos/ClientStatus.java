package com.lizana.customermicroservice.domain.objetos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientStatus {
    private boolean accountHolder;
    private boolean signatory;
}
