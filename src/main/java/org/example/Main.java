package org.example;

import java.nio.file.Path;
import java.util.Scanner;

public class Main {

    static void main() {
        Scanner in = new Scanner(System.in);
        System.out.println("choose an option:");
        System.out.println("e -> encrypt");
        System.out.println("d -> decrypt");
        String choice = in.nextLine().trim().toLowerCase();
        if (choice.equals("e")) {
            System.out.println ("Enter the path:");
            String input = in.nextLine().trim();
            Path path = Path.of(input);
            FileService fileService = new FileService();
            String content = fileService.readFile(path);
            EncryptionService encryptionService = new EncryptionService();
            String encryptedContent = encryptionService.encrypt(content);
            int key = encryptionService.getKey();
            EncryptionResult result = fileService.writePaths(path, encryptedContent, key);
            System.out.println("Encrypted file:" + result.getEncryptedFile());
            System.out.println("Key file:" + result.getKeyFile());
        }
        else if (choice.equals("d"))
            System.out.println("boo");
        else
            System.out.println("Invalid choice");
    }
}
