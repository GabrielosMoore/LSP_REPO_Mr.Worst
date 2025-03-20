package org.howard.edu.lsp.midterm.question3;

/**
 * Test driver for VotingMachine implementation
 */
public class VotingMachineTest {
    public static void main(String[] args) {
        VotingMachine vm = new VotingMachine();

        // Adding candidates
        vm.addCandidate("Alice");
        vm.addCandidate("Bob");
        vm.addCandidate("Charlie");

        // Casting votes
        vm.castVote("Alice");
        vm.castVote("Alice");
        vm.castVote("Bob");
        vm.castVote("Charlie");
        vm.castVote("Charlie");
        vm.castVote("Charlie");
        
        // Attempt to vote for non-existent candidate
        boolean success = vm.castVote("Eve");
        System.out.println("Vote for Eve successful? " + success);

        // Display results
        System.out.println("Winner: " + vm.getWinner());
    }
} 