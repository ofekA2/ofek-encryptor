package org.example.services;

import org.example.models.EncryptionResult;

import java.util.Random;

public class EncryptionService {

    public EncryptionResult encrypt(String content) {
        int key = new Random().nextInt(65536);
        StringBuilder encrypted = new StringBuilder();
        for (char c : content.toCharArray()) {
            char encryptedChar = (char) ((c + key) % 65536);
            encrypted.append(encryptedChar);
        }
        return new EncryptionResult(encrypted.toString(), key);
    }

    public String decrypt(String content, int key) {
        StringBuilder decrypted = new StringBuilder();
        for (char c : content.toCharArray()) {
            char decryptedChar = (char) ((c - key + 65536) % 65536);
            decrypted.append(decryptedChar);
        }
        return decrypted.toString();
    }
}
