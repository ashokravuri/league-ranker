package com.ranking.example.service;

import com.ranking.example.dto.Team;
import com.ranking.example.exception.InvalidResultFormatException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RankingCalculatorImplTest {


    public static final int WIN_POINTS = 3;
    public static final int LOSS_POINTS = 0;
    public static final int TIE_POINTS = 1;
    private RankingCalculator rankingCalculator;

    @BeforeEach
    public void setUp(){
        rankingCalculator = new RankingCalculatorImpl();
    }

    @Test
    void parseResult_ValidInput_CalculatesRankings() throws InvalidResultFormatException {
        List<String> results = Arrays.asList(
                "TeamA 3, TeamB 1",
                "TeamC 0, TeamD 0",
                "TeamE 2, TeamF 2"
        );

        List<Team> teams = rankingCalculator.parseResult(results);

        assertEquals(6, teams.size());
        // Validate the rankings based on the expected output
        assertEquals("TeamA", teams.get(0).getName());
        assertEquals("TeamC", teams.get(1).getName());
        assertEquals("TeamD", teams.get(2).getName());
        assertEquals("TeamE", teams.get(3).getName());
        assertEquals("TeamF", teams.get(4).getName());
        assertEquals("TeamB", teams.get(5).getName());
    }


    @Test
    void parseResult_Draws_Gives1Point() throws InvalidResultFormatException {
        List<String> results = Arrays.asList(
                "TeamA 2, TeamB 2",
                "TeamC 1, TeamD 1"
        );

        List<Team> teams = rankingCalculator.parseResult(results);

        assertEquals(4, teams.size());

        Team teamAExpected = new Team("TeamA");
        teamAExpected.addPoints(TIE_POINTS);

        Team teamBExpected = new Team("TeamB");
        teamBExpected.addPoints(TIE_POINTS);

        Team teamCExpected = new Team("TeamC");
        teamCExpected.addPoints(TIE_POINTS);

        Team teamDExpected = new Team("TeamD");
        teamDExpected.addPoints(TIE_POINTS);

        assertEquals(teamAExpected, teams.get(0));
        assertEquals(teamBExpected, teams.get(1));
        assertEquals(teamCExpected, teams.get(2));
        assertEquals(teamDExpected, teams.get(3));
    }

    @Test
    void parseResult_HomeTeamWins_Gives3Points() throws InvalidResultFormatException {
        List<String> results = Arrays.asList(
                "TeamA 3, TeamB 1",
                "TeamC 2, TeamD 0"
        );

        List<Team> teams = rankingCalculator.parseResult(results);

        assertEquals(4, teams.size());

        Team teamAExpected = new Team("TeamA");
        teamAExpected.addPoints(WIN_POINTS);

        Team teamBExpected = new Team("TeamB");
        teamBExpected.addPoints(LOSS_POINTS);

        Team teamCExpected = new Team("TeamC");
        teamCExpected.addPoints(WIN_POINTS);

        Team teamDExpected = new Team("TeamD");
        teamDExpected.addPoints(LOSS_POINTS);

        assertEquals(teamAExpected, teams.get(0));
        assertEquals(teamCExpected, teams.get(1));
        assertEquals(teamBExpected, teams.get(2));
        assertEquals(teamDExpected, teams.get(3));
    }


    @Test
    void parseResult_AwayTeamWins_Gives3Points() throws InvalidResultFormatException {
        List<String> results = Arrays.asList(
                "TeamA 1, TeamB 2",
                "TeamC 0, TeamD 1"
        );

        List<Team> teams = rankingCalculator.parseResult(results);

        assertEquals(4, teams.size());

        Team teamAExpected = new Team("TeamA");
        teamAExpected.addPoints(LOSS_POINTS);

        Team teamBExpected = new Team("TeamB");
        teamBExpected.addPoints(WIN_POINTS);

        Team teamCExpected = new Team("TeamC");
        teamCExpected.addPoints(LOSS_POINTS);

        Team teamDExpected = new Team("TeamD");
        teamDExpected.addPoints(WIN_POINTS);

        assertEquals(teamBExpected, teams.get(0));
        assertEquals(teamDExpected, teams.get(1));
        assertEquals(teamAExpected, teams.get(2));
        assertEquals(teamCExpected, teams.get(3));
    }


    @Test
    void parseResult_InvalidInput_ThrowsException() {
        List<String> results = Arrays.asList(
                "InvalidFormat",
                "TeamA 2, TeamB 0"
        );

        assertThrows(InvalidResultFormatException.class, () -> rankingCalculator.parseResult(results));
    }



}