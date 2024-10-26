package com.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadedServer {

    private static final int PORT = 1234; // Port to listen on
    private static final int MAX_THREADS = 100; // Limit of concurrent connections
    private static ExecutorService threadPool = Executors.newFixedThreadPool(MAX_THREADS);

    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.out.println("Usage: java -jar assignment3.jar -l <port> -p <pattern>");
            return;
        }
        int port = Integer.parseInt(args[1]); // Expecting -l <port> format
        String searchPattern = args[3]; // Expecting -p <pattern>
        System.out.println("Server starting on port: " + port + " with pattern: " + searchPattern);

        SharedLinkedList sharedList = new SharedLinkedList();

        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Server started. Listening on port: " + PORT);

        // Start pattern analysis thread(s)
        PatternAnalysis patternAnalysis = new PatternAnalysis(sharedList, searchPattern);
        new Thread(patternAnalysis).start();

        while (true) {
            Socket clientSocket = serverSocket.accept();
            threadPool.execute(new ClientHandler(clientSocket, sharedList));
        }
    }
}
