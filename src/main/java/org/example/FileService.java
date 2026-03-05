package org.example;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileService {

    public String readFile (Path path) {
        try {
            return Files.readString(path, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file", e);
        }
    }

    public EncryptionResult writePaths (Path originalPath, String encryptedContent, int key) {
        String fileName = originalPath.getFileName().toString();
        int dotIndex = fileName.lastIndexOf(".");
        String name = fileName.substring(0, dotIndex);
        String extension = fileName.substring(dotIndex);
        Path encryptedPath = originalPath.getParent().resolve(name + "_encrypted" + extension);
        Path keyPath = originalPath.getParent().resolve(name + "_key.txt");
        try {
            Files.writeString(encryptedPath, encryptedContent, StandardCharsets.UTF_8);
            Files.writeString(keyPath, String.valueOf(key), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write files", e);
        }
        return new EncryptionResult (encryptedPath, keyPath);
    }
}
