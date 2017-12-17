package com.caysever.dockermoon.enumtype;

public enum NodeRole {

    MANAGER("manager"), WORKER("worker");

    private String value;

    NodeRole(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
