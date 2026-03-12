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

    public Path getValidFilePath(IOHandler io, String message) {
        while (true) {
            io.print(message);
            String inputPath = io.readLine();
            Path path = Path.of(inputPath);
            if (Files.exists(path) && Files.isRegularFile(path)) {
                if (path.toString().endsWith(".txt")) {
                    return path;
                }
                io.print("Only .txt files are supported");
            } else {
                io.print("Invalid path, please try again");
            }
        }
    }
}
