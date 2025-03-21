package org.howard.edu.lsp.midterm.question4;

import java.util.*;

public class WordProcessor {
    private String sentence;

    public WordProcessor(String sentence) {
        this.sentence = sentence;
    }

    public List<String> findLongestWords() {
        if (sentence == null || sentence.trim().isEmpty()) {
            return new ArrayList<>();
        }

        String[] words = sentence.trim().split("\\s+");
        List<String> longestWords = new ArrayList<>();
        int maxLength = 0;

        // Find max length
        for (String word : words) {
            maxLength = Math.max(maxLength, word.length());
        }

        // Collect all words of max length
        for (String word : words) {
            if (word.length() == maxLength) {
                longestWords.add(word);
            }
        }

        return longestWords;
    }
} 