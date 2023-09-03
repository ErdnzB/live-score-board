package sportsradar.scoreboard.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sportsradar.scoreboard.exceptions.GameScoreCannotBeNegative;
import sportsradar.scoreboard.model.Game;
import sportsradar.scoreboard.model.GameResult;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ScoreboardServiceTest {

    private ScoreboardService serviceUnderTest;

    @BeforeEach
    void setup() {
        serviceUnderTest = new ScoreboardService();
    }

    @Test
    void shouldStartNewGame() {
        // given
        Game game = getNewTestGame();

        // when
        serviceUnderTest.startNewGame(game, LocalDateTime.now());
        List<Game> gamesInProgress = serviceUnderTest.getSummaryOfGamesInProgress();

        // then
        assertThat(gamesInProgress).contains(game);
        assertTrue(game.isInProgress());
        assertTrue(game.isActive());
    }

    @Test
    void shouldThrowAnExceptionWhenStartingGameInProgress() {
        // given
        Game game = getNewTestGame();
        serviceUnderTest.startNewGame(game, LocalDateTime.now());

        // when && then
        assertThrows(IllegalStateException.class, () -> serviceUnderTest.startNewGame(game, LocalDateTime.now()));
    }

    @Test
    void shouldUpdateScore() {
        // given
        Game game = getNewTestGame();
        int homeScore = 1;
        int awayScore = 0;
        serviceUnderTest.startNewGame(game, LocalDateTime.now());

        // when
        serviceUnderTest.updateScore(game, new GameResult(homeScore, awayScore));

        // then
        assertEquals(homeScore, game.getGameResult().getHomeTeamScore());
        assertEquals(awayScore, game.getGameResult().getAwayTeamScore());
    }

    @Test
    void shouldFinishGame() {
        // given
        Game game = getNewTestGame();
        serviceUnderTest.startNewGame(game, LocalDateTime.now());

        // when
        serviceUnderTest.finishGame(game);
        List<Game> gamesInProgress = serviceUnderTest.getSummaryOfGamesInProgress();

        // then
        assertThat(gamesInProgress).doesNotContain(game);
        assertFalse(game.isInProgress());
        assertFalse(game.isActive());
    }

    @Test
    void shouldGetSummaryOfGamesSorted() {
        // given
        Game mexicoCanada = new Game("Mexico", "Canada");
        Game spainBrazil = new Game("Spain", "Brazil");
        Game germanyFrance = new Game("Germany", "France");
        Game uruguayItaly = new Game("Uruguay", "Italy");
        Game argentinaAustralia = new Game("Argentina", "Australia");

        serviceUnderTest.startNewGame(mexicoCanada, LocalDateTime.now());
        serviceUnderTest.startNewGame(spainBrazil, LocalDateTime.now().plusMinutes(3));
        serviceUnderTest.startNewGame(germanyFrance, LocalDateTime.now().plusMinutes(6));
        serviceUnderTest.startNewGame(uruguayItaly, LocalDateTime.now().plusMinutes(9));
        serviceUnderTest.startNewGame(argentinaAustralia, LocalDateTime.now().plusMinutes(12));

        serviceUnderTest.updateScore(mexicoCanada, new GameResult(0, 5));
        serviceUnderTest.updateScore(spainBrazil, new GameResult(10, 2));
        serviceUnderTest.updateScore(germanyFrance, new GameResult(2, 2));
        serviceUnderTest.updateScore(uruguayItaly, new GameResult(6, 6));
        serviceUnderTest.updateScore(argentinaAustralia, new GameResult(3, 1));

        // when
        List<Game> gamesInProgress = serviceUnderTest.getSummaryOfGamesInProgress();

        // then
        assertThat(gamesInProgress).containsExactly(
                uruguayItaly, spainBrazil, mexicoCanada, argentinaAustralia, germanyFrance);
    }

    private static Game getNewTestGame() {
        return new Game("Trabzonspor", "Beşiktaş");
    }

    @Test
    void shouldGetNegativeScoreException() {
        // given
        Game game = getNewTestGame();
        int homeScore = 1;
        int awayScore = -3;
        serviceUnderTest.startNewGame(game, LocalDateTime.now());

        // when && then
        assertThrows(GameScoreCannotBeNegative.class, () -> serviceUnderTest.updateScore(game, new GameResult(homeScore, awayScore)));

    }


}