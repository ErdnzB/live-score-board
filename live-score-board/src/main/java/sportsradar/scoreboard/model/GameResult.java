package sportsradar.scoreboard.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import sportsradar.scoreboard.exceptions.GameScoreCannotBeNegative;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class GameResult {

    private final int homeTeamScore;
    private final int awayTeamScore;

    public GameResult(final int homeTeamScore, final int awayTeamScore) {
        validateScore(homeTeamScore);
        validateScore(awayTeamScore);
        this.homeTeamScore = homeTeamScore;
        this.awayTeamScore = awayTeamScore;
    }

    private void validateScore(final int teamScore) {
        if (teamScore < 0) {
            throw new GameScoreCannotBeNegative(teamScore);
        }
    }

}