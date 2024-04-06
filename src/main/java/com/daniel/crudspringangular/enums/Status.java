package com.daniel.crudspringangular.enums;

public enum Status {
    
    ACTIVE("Ativo"), INACTIVE("inativo");

    private String value;

    private Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
