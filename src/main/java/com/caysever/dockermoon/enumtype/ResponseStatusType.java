package com.caysever.dockermoon.enumtype;

public enum ResponseStatusType {

    SUCCESS("success"), FAILURE("failiure");

    private String type;

    ResponseStatusType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
