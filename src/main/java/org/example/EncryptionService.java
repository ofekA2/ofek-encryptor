package org.example;

import java.util.Random;

public class EncryptionService {
    private int key;

    public EncryptionService () {
        Random random = new Random();
        this.key = Math.abs(random.nextInt());
    }

    public int getKey() {
        return key;
    }

    public String encrypt (String content) {
        StringBuilder encrypted = new StringBuilder();
        for (char c : content.toCharArray()) {
            char encryptedChar = (char)(c + this.key);
            encrypted.append(encryptedChar);
        }
        return encrypted.toString();
    }
}
