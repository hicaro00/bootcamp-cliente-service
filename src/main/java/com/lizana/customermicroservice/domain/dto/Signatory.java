package com.lizana.customermicroservice.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Signatory {

    private String name;
    private String address;
    private String identifier;


}
