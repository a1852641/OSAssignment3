package com.example;

import java.util.HashMap;
import java.util.Map;

class PatternAnalysis implements Runnable {
    private SharedLinkedList sharedList;
    private String searchPattern;

    public PatternAnalysis(SharedLinkedList sharedList, String searchPattern) {
        this.sharedList = sharedList;
        this.searchPattern = searchPattern;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(5000); // Configurable interval
                analyzePattern();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void analyzePattern() {
        Map<String, Integer> bookPatternCounts = new HashMap<>();
        // Traverse the shared list to count occurrences of the pattern for each book title
        synchronized (sharedList) {
            SharedLinkedList.Node current = sharedList.getHead();
            while (current != null) {
                if (current.data.contains(searchPattern)) {
                    bookPatternCounts.put(current.bookTitle,
                            bookPatternCounts.getOrDefault(current.bookTitle, 0) + 1);
                }
                current = current.next;
            }
        }
        
        System.out.println("Analyzing pattern: \"" + searchPattern + "\"");
        bookPatternCounts.entrySet().stream()
            .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue())) // Sort by frequency
            .forEach(entry -> System.out.println("Book: " + entry.getKey() + " | Occurrences: " + entry.getValue()));
    }
}
