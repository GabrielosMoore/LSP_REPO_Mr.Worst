package org.howard.edu.lsp.midterm.question3;

import java.util.*;

public class VotingMachine {
    private Map<String, Integer> votes = new HashMap<>();

    public void addCandidate(String name) {
        votes.putIfAbsent(name, 0);
    }

    public boolean castVote(String name) {
        if (!votes.containsKey(name)) {
            return false;
        }
        votes.put(name, votes.get(name) + 1);
        return true;
    }

    public String getWinner() {
        if (votes.isEmpty()) {
            return "No candidates";
        }

        String winner = null;
        int maxVotes = -1;

        for (Map.Entry<String, Integer> entry : votes.entrySet()) {
            if (entry.getValue() > maxVotes) {
                maxVotes = entry.getValue();
                winner = entry.getKey();
            }
        }

        return winner + " WINS with " + maxVotes + " votes!!";
    }
} 