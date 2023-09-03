package sportsradar.scoreboard.service.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sportsradar.scoreboard.exceptions.GameAlreadyFinishedException;
import sportsradar.scoreboard.model.Game;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GameStateValidatorTest {

    private GameStateValidator serviceUnderTest;

    @BeforeEach
    void setup() {
        serviceUnderTest = new GameStateValidator();
    }

    @Test
    void shouldThrowAnExceptionWhenValidatingGameToBeFinishedForNewGame() {
        // given
        Game game = new Game("Trabzonspor", "Napoli");

        // when && then
        assertThrows(GameAlreadyFinishedException.class, () -> serviceUnderTest.validateIfGameCanBeFinished(game));
    }

    @Test
    void shouldThrowAnExceptionWhenValidatingGameToBeFinishedForFinishedGame() {
        // given
        Game game = new Game("Trabzonspor", "Inter");
        game.startGame(LocalDateTime.now());
        game.finishGame();

        // when && then
        assertThrows(GameAlreadyFinishedException.class, () -> serviceUnderTest.validateIfGameCanBeFinished(game));
    }

    @Test
    void shouldThrowAnExceptionWhenValidatingGameToBeFinishedForFinishedGameWithNullDate() {
        // given
        Game game = new Game("Trabzonspor", "Inter");
        game.startGame(null);
        game.finishGame();

        // when && then
        assertThrows(GameAlreadyFinishedException.class, () -> serviceUnderTest.validateIfGameCanBeFinished(game));
    }

    @Test
    void shouldNotThrowAnExceptionWhenValidatingGameToBeFinishedForOngoingGame() {
        // given
        var game = new Game("Trabzonspor", "Roterdam");

        // when
        game.startGame(LocalDateTime.now());

        // then
        assertDoesNotThrow(() -> serviceUnderTest.validateIfGameCanBeFinished(game));
    }

}