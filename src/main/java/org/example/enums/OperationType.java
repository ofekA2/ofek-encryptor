
package org.example.enums;

public enum OperationType {

    ENCRYPT("e"),
    DECRYPT("d"),
    EXIT("x");

    private final String code;

    OperationType(String code) {
        this.code = code;
    }

    public static OperationType fromInput(String input) {
        for (OperationType op : values()) {
            if (op.code.equals(input)) {
                return op;
            }
        }
        return null;
    }
}
