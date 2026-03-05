package org.example;

import java.nio.file.Path;

public class EncryptionResult {
    private final Path encryptedFile;
    private final Path keyFile;

    public EncryptionResult (Path encryptedFile, Path keyFile) {
        this.encryptedFile = encryptedFile;
        this.keyFile = keyFile;
    }

    public Path getEncryptedFile() {
        return encryptedFile;
    }

    public Path getKeyFile() {
        return keyFile;
    }
}
