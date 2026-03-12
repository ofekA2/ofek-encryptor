package org.example;

import org.example.enums.OperationType;
import org.example.models.EncryptionResult;
import org.example.services.EncryptionService;
import org.example.services.FileService;
import org.example.services.IOHandler;
import org.example.services.PathService;

import java.io.IOException;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) throws IOException {
        IOHandler io = new IOHandler();
        FileService fileService = new FileService();
        EncryptionService encryptionService = new EncryptionService();
        OperationType operation;
        do {
            operation = askUserOperation(io);
            if (operation == OperationType.ENCRYPT) {
                handleEncryption(io, fileService, encryptionService);
            }
            if (operation == OperationType.DECRYPT) {
                handleDecryption(io, fileService, encryptionService);
            }
        } while (operation != OperationType.EXIT);
        io.print("Goodbye");
    }

    private static OperationType askUserOperation(IOHandler io) {
        while (true) {
            io.print("Choose an option - e for encryption / d for decryption / x for exit:");
            String choice = io.readLine().toLowerCase();
            switch (choice) {
                case "e":
                    return OperationType.ENCRYPT;
                case "d":
                    return OperationType.DECRYPT;
                case "x":
                    return OperationType.EXIT;
                default:
                    io.print("Invalid option, please try again");
            }
        }
    }

    private static void handleEncryption(IOHandler io, FileService fileService, EncryptionService encryptionService) throws IOException {
        Path path = fileService.getValidFilePath(io, "Enter the path to the file:");
        String content = fileService.readFile(path);
        EncryptionResult result = encryptionService.encrypt(content);
        Path encryptedPath = PathService.buildEncryptedPath(path);
        Path keyPath = PathService.buildKeyPath(path);
        fileService.writeFile(encryptedPath, result.getEncryptedContent());
        fileService.writeFile(keyPath, String.valueOf(result.getKey()));
        io.print("Encrypted file: " + encryptedPath);
        io.print("Key file: " + keyPath);
    }

    private static void handleDecryption(IOHandler io, FileService fileService, EncryptionService encryptionService) throws IOException {
        Path encryptedPath = fileService.getValidFilePath(io, "Enter the encrypted file:");
        Path keyPath = fileService.getValidFilePath(io, "Enter the key file:");
        String encryptedContent = fileService.readFile(encryptedPath);
        int key = Integer.parseInt(fileService.readFile(keyPath).trim());
        String decryptedContent = encryptionService.decrypt(encryptedContent, key);
        Path outputPath = PathService.buildDecryptedPath(encryptedPath);
        fileService.writeFile(outputPath, decryptedContent);
        io.print("Decrypted file: " + outputPath);
    }
}
