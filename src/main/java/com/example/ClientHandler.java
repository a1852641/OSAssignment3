package com.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

class ClientHandler implements Runnable {
    private Socket clientSocket;
    private static int bookCount = 0;
    private SharedLinkedList sharedList;
    private int connectionId;

    public ClientHandler(Socket socket, SharedLinkedList sharedList) {
        this.clientSocket = socket;
        this.sharedList = sharedList;
        this.connectionId = ++bookCount; // Unique ID per connection
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            String line;
            StringBuilder bookContent = new StringBuilder();
            String title = "";

            while ((line = in.readLine()) != null) {
                if (title.isEmpty()) {
                    title = line + " [Connection " + connectionId + "]"; // Add unique ID to title
                }
                bookContent.append(line).append("\n");
                sharedList.addNode(line, title);
            }

            writeBookToFile(title, bookContent.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private synchronized void writeBookToFile(String title, String content) throws IOException {
        String fileName = String.format("book_%02d.txt", connectionId);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("Title: " + title + "\n\n");
            writer.write(content);
        }
        System.out.println("Book saved as " + fileName);
    }
}
