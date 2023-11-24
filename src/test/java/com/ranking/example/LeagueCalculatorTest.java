package com.ranking.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class LeagueCalculatorTest {

    private final InputStream originalSystemIn = System.in;
    private final PrintStream originalSystemOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp(TestInfo testInfo) {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown(TestInfo testInfo) {
        System.setIn(originalSystemIn);
        System.setOut(originalSystemOut);
    }


    @Test
    public void testLeagueCalculatorWithInput() {
        // Arrange
        String input = "Lions 3, Snakes 3\n" +
                "Tarantulas 1, FC Awesome 0\n" +
                "Lions 1, FC Awesome 1\n" +
                "Tarantulas 3, Snakes 1\n" +
                "Lions 4, Grouches 0\n";

        // Redirect System.in to read from the provided input
        System.setIn(new ByteArrayInputStream(input.getBytes()));


        LeagueCalculator.main(new String[0]);


        String expectedOutput =
                "1. Tarantulas, 6 pts" + System.lineSeparator() +
                        "2. Lions, 5 pts" + System.lineSeparator() +
                        "3. FC Awesome, 1 pt" + System.lineSeparator() +
                        "4. Snakes, 1 pt" + System.lineSeparator() +
                        "5. Grouches, 0 pts";

        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }

}
