package com.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class PatternAnalysis implements Runnable { // Implement Runnable
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
                Thread.sleep(5000); // Run every 5 seconds
                analyzePattern();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void analyzePattern() {
        Map<String, Integer> bookPatternCounts = new HashMap<>();
        
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

        if (!bookPatternCounts.isEmpty()) {
            List<Map.Entry<String, Integer>> sortedBooks = new ArrayList<>(bookPatternCounts.entrySet());
            sortedBooks.sort(Comparator.comparingInt((Map.Entry<String, Integer> entry) -> entry.getValue()).reversed());
            
            System.out.println("\nBook titles sorted by most frequent occurrences of pattern:");
            int rank = 1;
            for (Map.Entry<String, Integer> entry : sortedBooks) {
                System.out.printf("%d --> Book: %s, Pattern: \"%s\", Frequency: %d%n",
                        rank, entry.getKey(), searchPattern, entry.getValue());
                rank++;
            }
        }
    }
}
