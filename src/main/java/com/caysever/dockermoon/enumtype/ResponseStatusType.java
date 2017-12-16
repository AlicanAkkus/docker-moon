package com.caysever.dockermoon.enumtype;

public enum ResponseStatusType {

    SUCCESS("success"), FAILURE("failiure");

    private String value;

    ResponseStatusType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
