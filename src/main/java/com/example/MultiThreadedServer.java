package com.example;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class MultiThreadedServer {

    private static final int PORT = 1234; // Port to listen on
    private static final int MAX_THREADS = 100; // Limit of concurrent connections
    private static ExecutorService threadPool = Executors.newFixedThreadPool(MAX_THREADS);

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Server started. Listening on port: " + PORT);

        while (true) {
            Socket clientSocket = serverSocket.accept(); // Accept client connection
            threadPool.execute(new ClientHandler(clientSocket)); // Handle client in a new thread
        }
    }
}
