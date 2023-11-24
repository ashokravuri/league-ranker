package com.span.digital.service;

import com.span.digital.dto.Team;
import com.span.digital.exception.InvalidResultFormatException;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Implementation of the {@link RankingCalculator} interface that parses match results
 * and calculates team rankings based on predefined scoring rules.
 */
public class RankingCalculatorImpl implements RankingCalculator {

    /**
     * Regular expression pattern for matching team names and scores in the format: 'TeamName scoreNumber'
     */
    private static final Pattern TEAM_SCORE_PATTERN = Pattern.compile("([a-zA-Z\\s]+) (\\d+)");

    /**
     * Parses the given list of match results and calculates team rankings.
     *
     * @param results List of match results in the format 'Team1 score1, Team2 score2'
     * @return List of Team objects representing the calculated rankings.
     * @throws InvalidResultFormatException If the input result format is invalid.
     */
    @Override
    public List<Team> parseResult(List<String> results) throws InvalidResultFormatException {
        Map<String, Team> teams = new HashMap<>();

        for (String result : results) {
            String message = "Invalid score format in result: " + result;
            String[] parts = result.split(",\\s+");
            if (parts.length != 2) {
                throw new InvalidResultFormatException(message);
            }

            try {
                Matcher homeTeamMatcher = TEAM_SCORE_PATTERN.matcher(parts[0]);
                Matcher awayTeamMatcher = TEAM_SCORE_PATTERN.matcher(parts[1]);

                // Check if the pattern matches and extract team name and score
                if (homeTeamMatcher.matches() && awayTeamMatcher.matches()) {
                    String homeTeamName = homeTeamMatcher.group(1);
                    int homeScore = Integer.parseInt(homeTeamMatcher.group(2));

                    String awayTeamName = awayTeamMatcher.group(1);
                    int awayScore = Integer.parseInt(awayTeamMatcher.group(2));

                    Team homeTeam = teams.computeIfAbsent(homeTeamName, Team::new);
                    Team awayTeam = teams.computeIfAbsent(awayTeamName, Team::new);

                    int homePoints, awayPoints;

                    if (homeScore == awayScore) {
                        homePoints = 1;
                        awayPoints = 1;
                    } else {
                        homePoints = homeScore > awayScore ? 3 : 0;
                        awayPoints = 3 - homePoints;
                    }

                    homeTeam.addPoints(homePoints);
                    awayTeam.addPoints(awayPoints);
                } else {
                    throw new InvalidResultFormatException(message);
                }
            } catch (NumberFormatException e) {
                // Handle the case where score parsing fails

                throw new InvalidResultFormatException(message);
            }
        }

        List<Team> sortedTeams = new ArrayList<>(teams.values());
        sortedTeams.sort(Comparator.comparing(Team::getPoints).reversed().thenComparing(Team::getName));
        return sortedTeams;
    }
}