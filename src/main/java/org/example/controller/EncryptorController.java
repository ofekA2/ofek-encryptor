package org.example;

import org.example.enums.OperationType;
import org.example.services.EncryptionService;
import org.example.services.FileService;
import org.example.services.IOHandler;
import org.example.services.PathService;

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

    public OperationType askUserOperation() {
        OperationType operation;
        do {
            io.print("Choose option:");
            String input = io.readText();
            operation = OperationType.fromInput(input);
            if (operation == null) {
                io.print("Invalid option");
            }
        } while (operation == null);
        return operation;
    }
}
