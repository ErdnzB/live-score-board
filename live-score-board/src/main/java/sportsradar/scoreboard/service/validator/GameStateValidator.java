package sportsradar.scoreboard.service.validator;

import sportsradar.scoreboard.exceptions.GameAlreadyFinishedException;
import sportsradar.scoreboard.exceptions.GameAlreadyStartedException;
import sportsradar.scoreboard.model.Game;

public class GameStateValidator {

    public void validateIfGameCanBeStarted(final Game game) {
        if (game.isInProgress()) {
            throw new GameAlreadyStartedException();
        }
    }

    public void validateIfGameCanBeFinished(final Game game) {
        if (!game.isInProgress()) {
            throw new GameAlreadyFinishedException();
        }
    }

}