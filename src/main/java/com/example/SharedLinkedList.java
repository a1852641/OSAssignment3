package com.example;

class SharedLinkedList {
    private Node head;

    public synchronized void addNode(String data, String bookTitle) {
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

    }
    
    public synchronized int countPatternOccurrences(String pattern, String bookTitle) {
        Node temp = head;
        int count = 0;
        while (temp != null) {
            if (temp.bookTitle.equals(bookTitle) && temp.data.contains(pattern)) {
                count++;
            }
            temp = temp.next;
        }
        return count;
    }
    
    public synchronized void printBook(String title) {
        Node temp = head;
        System.out.println("Book: " + title);
        while (temp != null) {
            if (temp.bookTitle.equals(title)) {
                System.out.println(temp.data);
            }
            temp = temp.next;
        }
    }

    protected  Node getHead() {
        return head;
    }
    

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
