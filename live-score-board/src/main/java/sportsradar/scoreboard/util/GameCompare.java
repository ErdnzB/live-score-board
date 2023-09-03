package sportsradar.scoreboard.util;

import sportsradar.scoreboard.model.Game;
import sportsradar.scoreboard.model.GameResult;

import java.util.Comparator;

public class GameCompare implements Comparator<Game> {

    public int compare(final Game firstGame, final Game secondGame) {

        GameResult firstGameResult = firstGame.getGameResult();
        GameResult secondGameResult = secondGame.getGameResult();

        int aggregatedFirstGameScore = getAggregatedScoreFromGameResult(firstGameResult);
        int aggregatedSecondGameScore = getAggregatedScoreFromGameResult(secondGameResult);

        if (aggregatedSecondGameScore == aggregatedFirstGameScore) {
            return secondGame.getStartTime().compareTo(firstGame.getStartTime());
        }

        return Integer.compare(aggregatedSecondGameScore, aggregatedFirstGameScore);
    }

    private int getAggregatedScoreFromGameResult(final GameResult gameResult) {
        return gameResult.getHomeTeamScore() + gameResult.getAwayTeamScore();
    }

}