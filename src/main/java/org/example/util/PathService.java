
package org.example.services;

import java.nio.file.Path;

public class PathService {

    public Path buildEncryptedFilePath(Path originalPath) {
        String fileName = originalPath.getFileName().toString();
        int lastDot = fileName.lastIndexOf(".txt");
        String baseName = fileName.substring(0, lastDot);
        return originalPath.getParent().resolve(baseName + "_encrypted.txt");
    }

    public Path buildKeyFilePath(Path originalPath) {
        String name = originalPath.getFileName().toString().replace(".txt", "");
        return originalPath.getParent().resolve(name + "_key.txt");
    }

    public Path buildDecryptedFilePath(Path encryptedPath) {
        String name = encryptedPath.getFileName().toString().replace(".txt", "");
        return encryptedPath.getParent().resolve(name + "_decrypted.txt");
    }
}
