
package org.example.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileService {

    public String readFile(Path path) throws IOException {
        return Files.readString(path);
    }

    public void writeFile(Path path, String content) throws IOException {
        Files.writeString(path, content);
    }

    public void writeFile(Path path, Integer content) throws IOException {
        Files.writeString(path, content.toString());
    }

    public boolean isValidFile(Path path) {
        return Files.exists(path) &&
                Files.isRegularFile(path) &&
                path.toString().endsWith(".txt");
    }
}
