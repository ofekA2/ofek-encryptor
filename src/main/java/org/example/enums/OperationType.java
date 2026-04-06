package org.example.enums;

import java.util.Arrays;

public enum OperationType {

    ENCRYPT("e"),
    DECRYPT("d"),
    EXIT("x");

    private final String code;

    OperationType(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public static OperationType fromInput(String input) {
        return Arrays.stream(values()).filter(op -> op.code.equals(input)).findFirst().orElse(null);
    }

}
