package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {

    static void main() throws IOException {
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("choose an option - e for encryption / d for decryption / x for exit:");
            String choice = in.nextLine().trim().toLowerCase();
            if (choice.equals("e")) {
                handleEncryption (in);
            }
            else if (choice.equals("d")) {
                handleDecryption (in);
            }
            else if (choice.equals("x")) {
                System.out.println("Goodbye");
                break;
            }
            else
                System.out.println("Invalid choice, please try again");
        }
    }

    static Path validFile (Scanner in, String message) {
        while (true) {
            System.out.println(message);
            String input = in.nextLine().trim();
            Path path = Path.of(input);
            if (!Files.exists(path)) {
                System.out.println("File not found, please try again");
                continue;
            }
            if (!Files.isRegularFile(path)) {
                System.out.println("The path points to a directory, please enter a file");
                continue;
            }
            try {
                if (Files.size(path) == 0) {
                    System.out.println("The file is empty - nothing to encrypt");
                    continue;
                }
            } catch (IOException e) {
                System.out.println("Unable to access the file, please try another one");
                continue;
            }
            return path;
        }
    }

    private static void handleEncryption (Scanner in) {
        Path path = validFile(in, "Enter the path to the file:");
        FileService fileService = new FileService();
        EncryptionService encryptionService = new EncryptionService();
        String content = fileService.readFile(path);
        String encryptedContent = encryptionService.encrypt(content);
        int key = encryptionService.getKey();
        String fileName = path.getFileName().toString();
        if (fileName.contains("_encrypted")) {
            System.out.println("This file has already been encrypted");
            return;
        }
        try {
            EncryptionResult result = fileService.writePaths(path, encryptedContent, key);
            System.out.println("Encrypted file: " + result.getEncryptedFile());
            System.out.println("Key file: " + result.getKeyFile());
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void handleDecryption (Scanner in) throws IOException {
        int key;
        Path encryptedPath = validFile(in, "Enter the path to the encrypted file:");
        Path keyPath = validFile(in, "Enter the path to the key file:");
        String encryptedName = encryptedPath.getFileName().toString();
        String keyName = keyPath.getFileName().toString();
        String encryptedBase = encryptedName.replace("_encrypted.txt", "");
        String keyBase = keyName.replace("_key.txt", "");
        if (!encryptedBase.equals(keyBase)) {
            System.out.println("The key file does not match the encrypted file, please enter the correct files");
            return;
        }
        FileService fileService = new FileService();
        EncryptionService encryptionService = new EncryptionService();
        String encryptedContent = fileService.readFile(encryptedPath);
        String keyString = fileService.readFile(keyPath);
        try {
            key = Integer.parseInt(keyString.trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid key format");
            return;
        }
        String decryptedContent = encryptionService.decrypt(encryptedContent, key);
        String fileName = encryptedPath.getFileName().toString();
        if (!fileName.contains("_encrypted")) {
            System.out.println("This file is not encrypted");
            return;
        }
        int dotIndex = fileName.lastIndexOf(".");
        String name = (dotIndex == -1) ? fileName : fileName.substring(0, dotIndex);
        String extension = (dotIndex == -1) ? "" : fileName.substring(dotIndex);
        Path decryptedPath = encryptedPath.getParent().resolve(name + "_decrypted" + extension);
        Files.writeString(decryptedPath, decryptedContent);
        System.out.println("Decrypted file: " + decryptedPath);
    }
}
