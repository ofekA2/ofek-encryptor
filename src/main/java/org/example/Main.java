package org.example;

import org.example.manager.EncryptorManager;
import org.example.services.EncryptionService;
import org.example.util.FileService;
import org.example.util.IOHandler;
import org.example.util.PathService;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        IOHandler io = new IOHandler(scanner);
        FileService fileService = new FileService();
        PathService pathService = new PathService(io, fileService);
        Random random = new Random();
        EncryptionService encryptionService = new EncryptionService(random);
        EncryptorManager manager = new EncryptorManager(io, fileService, encryptionService, pathService);
        manager.run();
    }

}
