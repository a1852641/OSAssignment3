package com.example;

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
                // Search for the pattern and print results
                analyzePattern();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void analyzePattern() {
        // Implement pattern search and sorting logic here
        System.out.println("Analyzing pattern: " + searchPattern);
    }
}

