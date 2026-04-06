package org.example.models;

public class EncryptionResult {

    private final String encryptedContent;
    private final Integer key;

    public EncryptionResult(String encryptedContent, Integer key) {
        this.encryptedContent = encryptedContent;
        this.key = key;
    }

    public String getEncryptedContent() {
        return encryptedContent;
    }

    public Integer getKey() {
        return key;
    }

}
