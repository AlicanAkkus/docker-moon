package com.caysever.dockermoon.enumtype;

public enum NodeAvailabilityStatus {

    ACTIVE("active"), DRAIN("drain");

    private String value;

    NodeAvailabilityStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
