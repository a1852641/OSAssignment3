package com.example;

class SharedLinkedList {
    private Node head;
    
    public synchronized void addNode(String data, String bookTitle) {
        // Code to add a new node to the shared list
        Node newNode = new Node(data, bookTitle);
        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
        System.out.println("Added new node for book: " + bookTitle);
    }
    
    public synchronized void printBook(String title) {
        // Code to traverse the list and print all lines related to the book
    }

    // Inner Node class
    class Node {
        String data;
        String bookTitle;
        Node next;
        Node bookNext;
        
        Node(String data, String bookTitle) {
            this.data = data;
            this.bookTitle = bookTitle;
        }
    }
}
