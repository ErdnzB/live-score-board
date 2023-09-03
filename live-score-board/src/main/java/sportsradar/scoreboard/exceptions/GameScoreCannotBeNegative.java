package sportsradar.scoreboard.exceptions;

public class GameScoreCannotBeNegative extends IllegalArgumentException {
    public GameScoreCannotBeNegative(Integer gameScore) {
        super("Invalidated negative number: " + gameScore);
    }
}
