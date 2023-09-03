package sportsradar.scoreboard.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Game {

    private final Team homeTeam;
    private final Team awayTeam;
    private LocalDateTime startTime;
    private boolean isActive;

    public Game(final String homeTeamName, final String awayTeamName) {
        this.homeTeam = new Team(homeTeamName);
        this.awayTeam = new Team(awayTeamName);
    }

    public void startGame(LocalDateTime dateTime) {
        startTime = dateTime;
        isActive = true;
    }

    public void finishGame() {
        isActive = false;
    }

    public boolean isInProgress() {
        if (!isActive) {
            return false;
        }
        return startTime != null;
    }

    public GameResult getGameResult() {
        int homeTeamScore = homeTeam.getTeamScore();
        int awayTeamScore = awayTeam.getTeamScore();
        return new GameResult(homeTeamScore, awayTeamScore);
    }

    public void updateGameScore(final GameResult gameResult) {
        homeTeam.setTeamScore(gameResult.getHomeTeamScore());
        awayTeam.setTeamScore(gameResult.getAwayTeamScore());
    }

}
