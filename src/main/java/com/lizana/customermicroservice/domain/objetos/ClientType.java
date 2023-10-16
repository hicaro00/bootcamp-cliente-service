package com.lizana.customermicroservice.domain.objetos;

public enum ClientType {

    PERSONAL("cliente con cuenta personal"),
    BUSINESS("cliente con cuenta empresarial");

    private String descripcion;

    ClientType(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

}
