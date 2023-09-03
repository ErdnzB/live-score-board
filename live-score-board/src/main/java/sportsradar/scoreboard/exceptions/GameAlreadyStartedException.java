package sportsradar.scoreboard.exceptions;

public class GameAlreadyStartedException extends IllegalStateException {
    public GameAlreadyStartedException() {
        super("Game already started !");
    }
}