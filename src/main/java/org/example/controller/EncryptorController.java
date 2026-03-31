
package org.example.controller;

import org.example.enums.OperationType;
import org.example.models.EncryptionResult;
import org.example.services.EncryptionService;
import org.example.util.FileService;
import org.example.util.IOHandler;
import org.example.util.PathService;
import java.io.IOException;
import java.nio.file.Path;

public class EncryptorController {
    private final IOHandler io;
    private final FileService fileService;
    private final EncryptionService encryptionService;
    private final PathService pathService;

    public EncryptorController(IOHandler io, FileService fileService, EncryptionService encryptionService, PathService pathService) {
        this.io = io;
        this.fileService = fileService;
        this.encryptionService = encryptionService;
        this.pathService = pathService;
    }

    public void run() throws IOException {
        OperationType operation;
        do {
            operation = askUserOperation();
            if (operation == OperationType.ENCRYPT) {
                handleEncryption();
            }
            if (operation == OperationType.DECRYPT) {
                handleDecryption();
            }
        } while (operation != OperationType.EXIT);
        io.print("Goodbye");
    }

    public OperationType askUserOperation() {
        OperationType operation;
        do {
            io.print("Choose an option - e for encryption / d for decryption / x for exit:");
            String input = io.readText();
            operation = OperationType.fromInput(input);
            if (operation == null) {
                io.print("Invalid option, please try again");
            }
        } while (operation == null);
        return operation;
    }

    private void handleEncryption() throws IOException {
        Path path = askUserForValidPath("Enter the path to the file:");
        String content = fileService.readFile(path);
        EncryptionResult result = encryptionService.encrypt(content);
        saveEncryptionResult(path, result);
    }

    private void handleDecryption() throws IOException {
        Path encryptedPath = askUserForValidPath("Enter the path to the encrypted file:");
        Path keyPath = askUserForValidPath("Enter the path to the key file:");
        int key = Integer.parseInt(fileService.readFile(keyPath));
        String encryptedContent = fileService.readFile(encryptedPath);
        String decryptedContent = encryptionService.decrypt(encryptedContent, key);
        Path outputPath = pathService.buildDecryptedFilePath(encryptedPath);
        fileService.writeFile(outputPath, decryptedContent);
        io.print("Decrypted file: " + outputPath);
    }

    private void saveEncryptionResult(Path originalPath, EncryptionResult result) throws IOException {
        Path encryptedPath = pathService.buildEncryptedFilePath(originalPath);
        Path keyPath = pathService.buildKeyFilePath(originalPath);
        fileService.writeFile(encryptedPath, result.getEncryptedContent());
        fileService.writeFile(keyPath, result.getKey());
        io.print("Encrypted file: " + encryptedPath);
        io.print("Key file: " + keyPath);
    }

    private Path askUserForValidPath(String message) {
        Path path;
        boolean valid;
        do {
            io.print(message);
            String input = io.readText();
            path = Path.of(input);
            valid = fileService.isValidFile(path);
            if (!valid) {
                io.print("Invalid file, please try again.");
            }
        } while (!valid);
        return path;
    }
}
