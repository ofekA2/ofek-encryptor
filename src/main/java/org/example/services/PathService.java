package org.example.services;

import java.nio.file.Path;

public class PathService {

    public static Path buildEncryptedPath(Path originalPath) {
        String name = originalPath.getFileName().toString().replace(".txt", "");
        return originalPath.getParent().resolve(name + "_encrypted.txt");
    }

    public static Path buildKeyPath(Path originalPath) {
        String name = originalPath.getFileName().toString().replace(".txt", "");
        return originalPath.getParent().resolve(name + "_key.txt");
    }

    public static Path buildDecryptedPath(Path encryptedPath) {
        String name = encryptedPath.getFileName().toString().replace(".txt", "");
        return encryptedPath.getParent().resolve(name + "_decrypted.txt");
    }
}
