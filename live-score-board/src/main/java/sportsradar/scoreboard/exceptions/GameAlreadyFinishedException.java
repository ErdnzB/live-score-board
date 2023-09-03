package sportsradar.scoreboard.exceptions;

public class GameAlreadyFinishedException extends IllegalStateException {
    public GameAlreadyFinishedException() {
        super("Game Already Finished !");
    }
}