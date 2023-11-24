package com.ranking.example.service;

import com.ranking.example.dto.Team;

import java.util.List;

/**
 * Interface for calculating rankings based on match results.
 */
public interface RankingCalculator {

    /**
     * Parses the given list of match results and calculates team rankings.
     *
     * @param results List of match results as strings.
     * @return List of Team objects representing the calculated rankings.
     */
    List<Team> parseResult(List<String> results);
}
