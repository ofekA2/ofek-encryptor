
package org.example.services;

import org.example.models.EncryptionResult;
import java.util.Random;

public class EncryptionService {
    private final Random random;

    public EncryptionService(Random random) {
        this.random = random;
    }

    public EncryptionResult encrypt(String content) {
        int key = new Random().nextInt(65536);
        StringBuilder encryptedContent = new StringBuilder();
        for (char c : content.toCharArray()) {
            char encryptedChar = (char) ((c + key) % 65536);
            encryptedContent.append(encryptedChar);
        }
        return new EncryptionResult(encryptedContent.toString(), key);
    }

    public String decrypt(String content, int key) {
        StringBuilder decryptedContent = new StringBuilder();
        for (char c : content.toCharArray()) {
            char decryptedChar = (char) ((c - key + 65536) % 65536);
            decryptedContent.append(decryptedChar);
        }
        return decryptedContent.toString();
    }
}
