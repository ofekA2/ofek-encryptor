package org.example.util;

import java.nio.file.Path;
import java.util.Objects;

public class PathService {

    private final IOHandler io;
    private final FileService fileService;

    public PathService(IOHandler io, FileService fileService) {
        this.io = io;
        this.fileService = fileService;
    }

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

    public Path getFilePathFromUser() {
        Path path;
        do {
            String input = io.readText();
            path = Path.of(input);
            if (!fileService.isValidTxtFile(path)) {
                io.print("Invalid file, please try again");
                path = null;
            }
        } while (Objects.isNull(path));
        return path;
    }

}
