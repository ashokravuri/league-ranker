# league-ranker
The **league-ranker** is a command-line application that calculates and displays the ranking table for a sports league. It processes input results of games entered through the console and outputs the rankings in a specified format.

## Build and Run Instructions

1. To build the Maven application, run the following command:

```bash
mvn clean install
```

2. After successfully building the application, you can run it using the following command:

```bash
java -cp .\target\league-ranker-1.0-SNAPSHOT.jar com.ranking.example.LeagueCalculator
```

## Sample Input
```bash
Lions 3, Snakes 3
Tarantulas 1, FC Awesome 0
Lions 1, FC Awesome 1
Tarantulas 3, Snakes 1
Lions 4, Grouches 0
```
**Note:** Press "Enter" twice to see the result.
