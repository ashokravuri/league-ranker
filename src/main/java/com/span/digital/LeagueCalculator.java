package com.span.digital;

import com.span.digital.dto.Team;
import com.span.digital.service.RankingCalculator;
import com.span.digital.service.RankingCalculatorImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The LeagueCalculator class is a command-line application that calculates the ranking table for a league
 * based on input results of games.
 * The input is read from the console, and the results are printed to the console in the specified format.
 */
public class LeagueCalculator {

    /**
     * The main method that runs the league ranking calculation.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {

        // Initialize Scanner to read input from the console
        Scanner scanner = new Scanner(System.in);

        // Create a RankingCalculator implementation to parse and calculate the rankings
        RankingCalculator rankingCalculator = new RankingCalculatorImpl();

        // List to store input results
        ArrayList<String> inputList = new ArrayList<>();

        try {
            // Read input until an empty line is encountered
            while (scanner.hasNextLine()) {
                // Break if an empty line is encountered
                String resultInput = scanner.nextLine();
                if (resultInput.isEmpty()) {
                    break;
                } else {
                    // Add non-empty input lines to the list
                    inputList.add(resultInput);
                }
            }

            // Parse the input list and calculate team rankings
            List<Team> result = rankingCalculator.parseResult(inputList);

            // Print the calculated rankings
            printRanking(result);
        } catch (NumberFormatException e) {
            // Handle the case where input parsing fails (e.g., due to an invalid format)
            System.err.println("Error parsing input. Make sure the input follows the expected format.");
        } finally {
            scanner.close();
        }
    }

    /**
     * This method prints the ranking of the teams to the console.
     *
     * @param teams The list of teams to be ranked.
     */
    private static void printRanking(List<Team> teams) {
        int rank = 1;
        for (Team team : teams) {
            System.out.println(String.format("%d. %s, %d %s", rank, team.getName(), team.getPoints(), (team.getPoints() == 1 ? "pt" : "pts")));
            rank++;
        }
    }
}