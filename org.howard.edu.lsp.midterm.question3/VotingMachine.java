package org.howard.edu.lsp.midterm.question3;

import java.util.*;

/**
 * Implements a simple electronic voting machine
 */
public class VotingMachine {
    private Map<String, Integer> votes;
    private Set<String> candidates;
    
    public VotingMachine() {
        votes = new HashMap<>();
        candidates = new HashSet<>();
    }
    
    public void addCandidate(String name) {
        candidates.add(name);
        votes.put(name, 0);
    }
    
    public boolean castVote(String candidate) {
        if (!candidates.contains(candidate)) {
            return false;
        }
        votes.put(candidate, votes.get(candidate) + 1);
        return true;
    }
    
    public String getWinner() {
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