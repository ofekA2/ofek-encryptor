package org.example.models;

public class EncryptionResult {
    private final String encryptedContent;
    private final int key;

    public EncryptionResult(String encryptedContent, int key) {
        this.encryptedContent = encryptedContent;
        this.key = key;
    }

    public String getEncryptedContent() {
        return encryptedContent;
    }

    public int getKey() {
        return key;
    }
}
