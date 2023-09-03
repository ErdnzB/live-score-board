package sportsradar.scoreboard.model;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
public class Team {

    private final String teamName;
    private int teamScore;

}
