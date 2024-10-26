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

    public ClientHandler(Socket socket, SharedLinkedList sharedList) {
        this.clientSocket = socket;
        this.sharedList = sharedList;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            String line;
            StringBuilder bookContent = new StringBuilder();
            String title = "";

            while ((line = in.readLine()) != null) {
                if (title.isEmpty()) {
                    title = line;
                }
                bookContent.append(line).append("\n");
                sharedList.addNode(line, title); // Add line to shared list
                System.out.println("Received line: " + line);
            }

            writeBookToFile(title, bookContent.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private synchronized void writeBookToFile(String title, String content) throws IOException {
        bookCount++;
        String fileName = String.format("book_%02d.txt", bookCount);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("Title: " + title + "\n\n");
            writer.write(content);
        }
        System.out.println("Book saved as " + fileName);
    }
}
