import com.nebarrow.model.common.MatchScore;
import com.nebarrow.model.common.PlayerStats;
import com.nebarrow.service.MatchScoreCalculationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TennisScoreboardApplicationTest {

    private MatchScore matchScore;
    private static final MatchScoreCalculationService matchScoreCalculationService = new MatchScoreCalculationService();

    @BeforeEach
    void init() {
        var playerOneStats = PlayerStats.builder()
                .id(0)
                .name("Steve")
                .build();
        var playerTwoStats = PlayerStats.builder()
                .id(1)
                .name("Mark")
                .build();

        matchScore = MatchScore.builder()
                .players(new ArrayList<>(List.of(playerOneStats, playerTwoStats)))
                .build();
    }

    @Test
    public void TennisGame_WhenPlayerWinPointAtDeuce_ShouldDontStop() {
        matchScore.getPlayers().get(0).setPoints(3);
        matchScore.getPlayers().get(1).setPoints(3);
        assertEquals(3, matchScore.getPlayers().get(0).getPoints());
        matchScoreCalculationService.calculate(matchScore.getPlayers().get(0), matchScore);
        assertEquals(4, matchScore.getPlayers().get(0).getPoints());
    }

    @Test
    public void TennisGame_WhenPlayerOneWinPointWhenPlayerTwoHasZeroPoints_ShouldAddGameToPlayerOne() {
        matchScore.getPlayers().get(0).setPoints(3);
        assertEquals(0, matchScore.getPlayers().get(0).getGames());
        matchScoreCalculationService.calculate(matchScore.getPlayers().get(0), matchScore);
        assertEquals(1, matchScore.getPlayers().get(0).getGames());
    }

    @Test
    public void TennisGame_TieBreakGame_ShouldStartWhenGamesSame() {
        assertFalse(matchScore.isTieBreak());
        matchScore.getPlayers().get(0).setGames(6);
        matchScore.getPlayers().get(1).setGames(6);
        assertTrue(matchScore.isTieBreak());
    }
}
