package com.mongodb.kafka.connect.sink.cdc.couchdb;

public enum OperationType {
    INSERT("insert"),
    REPLACE("replace"),
    UPDATE("update"),
    DELETE("delete"),
    UNKNOWN("unknown");

    private final String value;

    OperationType(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static OperationType fromString(final String value) {
        for (OperationType operationType : OperationType.values()) {
            if (value.equalsIgnoreCase(operationType.value)) {
                return operationType;
            }
        }
        return UNKNOWN;
    }
}