
package org.example.services;

import org.example.models.EncryptionResult;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class FileService {

    public String readFile(Path path) throws IOException {
        return Files.readString(path);
    }

    public void writeFile(Path path, String content) throws IOException {
        Files.writeString(path, content);
    }

    public boolean isValidFile(Path path) {
        return Files.exists(path) &&
                Files.isRegularFile(path) &&
                path.toString().endsWith(".txt");
    }
}
