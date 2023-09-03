package sportsradar.scoreboard.service;

import sportsradar.scoreboard.model.Game;
import sportsradar.scoreboard.model.GameResult;
import sportsradar.scoreboard.service.validator.GameStateValidator;
import sportsradar.scoreboard.util.GameCompare;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ScoreboardService {

    private final GameStateValidator gameStateValidator;
    private final List<Game> liveGames;

    public ScoreboardService() {
        this.gameStateValidator = new GameStateValidator();
        this.liveGames = new ArrayList<>();
    }

    public void startNewGame(final Game game, LocalDateTime startTime) {
        assertIsGameStarted(game);
        game.startGame(startTime);
        liveGames.add(game);
    }

    public void finishGame(final Game game) {
        assertsIsGameFinished(game);
        game.finishGame();
        liveGames.remove(game);
    }

    public void updateScore(final Game game, final GameResult gameResult) {
        game.updateGameScore(gameResult);
    }

    public List<Game> getSummaryOfGamesInProgress() {
        return getGamesSortedByTotalCountAndStartTimeDescending(liveGames);
    }

    private List<Game> getGamesSortedByTotalCountAndStartTimeDescending(final List<Game> liveGames) {
        return liveGames.stream()
                .sorted(new GameCompare())
                .collect(Collectors.toList());
    }

    private void assertIsGameStarted(Game game) {
        gameStateValidator.validateIfGameCanBeStarted(game);
    }

    private void assertsIsGameFinished(Game game) {
        gameStateValidator.validateIfGameCanBeFinished(game);
    }
}
