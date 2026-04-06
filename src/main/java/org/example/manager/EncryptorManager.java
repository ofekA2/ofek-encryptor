package org.example.manager;

import org.example.enums.OperationType;
import org.example.models.EncryptionResult;
import org.example.services.EncryptionService;
import org.example.util.FileService;
import org.example.util.IOHandler;
import org.example.util.PathService;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Objects;

public class EncryptorManager {

    private final IOHandler io;
    private final FileService fileService;
    private final EncryptionService encryptionService;
    private final PathService pathService;

    public EncryptorManager(IOHandler io, FileService fileService, EncryptionService encryptionService, PathService pathService) {
        this.io = io;
        this.fileService = fileService;
        this.encryptionService = encryptionService;
        this.pathService = pathService;
    }

    public void run() throws IOException {
        OperationType operation;
        do {
            operation = getOperationFromUser();
            if (operation == OperationType.ENCRYPT) {
                handleEncryption();
            }
            if (operation == OperationType.DECRYPT) {
                handleDecryption();
            }
        } while (operation != OperationType.EXIT);
        io.print("Goodbye");
    }

    public OperationType getOperationFromUser() {
        OperationType operation;
        do {
            io.print("Choose an option:");
            for (OperationType op : OperationType.values()) {
                io.print(op.getCode() + " -> " + op.name().toLowerCase());
            }
            String input = io.readText();
            operation = OperationType.fromInput(input);
            if (Objects.isNull(operation)) {
                io.print("Invalid option, please try again");
            }
        } while (Objects.isNull(operation));
        return operation;
    }

    private void handleEncryption() throws IOException {
        io.print("Enter the path to the file:");
        Path path = pathService.getFilePathFromUser();
        String content = fileService.readFile(path);
        EncryptionResult result = encryptionService.encrypt(content);
        saveEncryptionResult(path, result);
    }

    private void handleDecryption() throws IOException {
        io.print("Enter the path to the encrypted file:");
        Path encryptedPath = pathService.getFilePathFromUser();
        io.print("Enter the path to the key file:");
        Path keyPath = pathService.getFilePathFromUser();
        Integer key = Integer.parseInt(fileService.readFile(keyPath));
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

}
