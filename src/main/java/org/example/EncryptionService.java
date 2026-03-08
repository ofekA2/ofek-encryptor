package org.example;

import java.util.Random;

public class EncryptionService {
    private int key;

    public EncryptionService () {
        Random random = new Random();
        this.key = random.nextInt(65536);
    }

    public int getKey() {
        return key;
    }

    public String encrypt (String content) {
        StringBuilder encrypted = new StringBuilder();
        for (char c : content.toCharArray()) {
            char encrypt = (char) ((c + this.key) % 65536);
            encrypted.append(encrypt);
        }
        return encrypted.toString();
    }

    public String decrypt (String content, int key) {
        StringBuilder decrypted = new StringBuilder();
        for (char c : content.toCharArray()) {
            char decrypt = (char) ((c - key + 65536) % 65536);
            decrypted.append(decrypt);
        }
        return decrypted.toString();
    }
}
