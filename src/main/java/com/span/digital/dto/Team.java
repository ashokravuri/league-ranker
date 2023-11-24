package com.span.digital.dto;

import java.util.Objects;

public class Team {

    private final String name;
    private int points;

    public Team(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public int getPoints() {
        return points;
    }

    public void addPoints(int points) {
        this.points += points;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return points == team.points && Objects.equals(name, team.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, points);
    }

    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                ", points=" + points +
                '}';
    }
}
