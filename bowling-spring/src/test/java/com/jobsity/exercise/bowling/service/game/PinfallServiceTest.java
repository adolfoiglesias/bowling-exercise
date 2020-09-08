package com.jobsity.exercise.bowling.service.game;

import com.jobsity.exercise.bowling.BowlingApplication;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


import com.jobsity.exercise.bowling.container.BowlingGameFactoryImpl;
import com.jobsity.exercise.bowling.container.BowlingGameFactory;
import com.jobsity.exercise.bowling.exception.BowlingCodeException;
import com.jobsity.exercise.bowling.exception.BowlingGameException;
import com.jobsity.exercise.bowling.model.BowlingGame;
import com.jobsity.exercise.bowling.model.ContainerGame;
import com.jobsity.exercise.bowling.model.Pinfall;
import com.jobsity.exercise.bowling.model.Player;
import com.jobsity.exercise.bowling.service.game.PinfallService;
import com.jobsity.exercise.bowling.service.game.PinfallServiceImpl;
import com.jobsity.exercise.bowling.service.game.PlayerService;
import com.jobsity.exercise.bowling.service.game.PlayerServiceImpl;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@SpringBootTest(classes =  BowlingApplication.class)
public class PinfallServiceTest {

    @InjectMocks
    private static PlayerService playerService = new PlayerServiceImpl();

    @InjectMocks
    private static PinfallService pinfallService = new PinfallServiceImpl();


    @BeforeEach
    void prepareGame() {
        BowlingGame game = BowlingGame.createGame();
        ContainerGame.setGame(game);
    }

    @AfterEach
    void restartGame() {
        BowlingGame game = ContainerGame.getGame();
        Set<Player> players = new HashSet<>();
        game.setPlayers(players);
    }

    @Test
    public void addResultStrikeToFrame1() throws BowlingGameException {

        String result1 = "10";

        Player player = playerService.addNewPlayer("Adolfo");
        Pinfall pinfall = pinfallService.recordPlay(player, result1);

        assertEquals(result1, pinfall.getValue1());
        assertEquals("X", pinfall.showPinfallValue().trim());
        assertEquals("", pinfall.getValue2());
        assertTrue(pinfall.isStrike());
        assertTrue(pinfall.isClosed());

    }

    @Test
    public void addResultSpareToFrame1() throws BowlingGameException {

        String result1 = "4";
        String result2 = "6";

        Player player = playerService.addNewPlayer("Adolfo");
        pinfallService.recordPlay(player, result1);
        Pinfall pinfall = pinfallService.recordPlay(player, result2);

        assertEquals("4", pinfall.getValue1());
        assertEquals("6", pinfall.getValue2());
        assertEquals("4 /", pinfall.showPinfallValue().trim());
        assertTrue(pinfall.isSpare());
        assertTrue(pinfall.isClosed());
    }

    @Test
    public void addResultRegularToFrame1() throws BowlingGameException {

        String result1 = "4";
        String result2 = "5";

        Player player = playerService.addNewPlayer("Adolfo");
        pinfallService.recordPlay(player, result1);
        Pinfall pinfall = pinfallService.recordPlay(player, result2);

        assertEquals(result1, pinfall.getValue1());
        assertEquals(result2, pinfall.getValue2());
        assertEquals("4 5", pinfall.showPinfallValue().trim());
        assertTrue(pinfall.isRegular());
        assertTrue(pinfall.isClosed());
    }

    @Test
    public void addResultInvalidToFrame1() throws BowlingGameException {

        String result1 = "w";

        Player player = playerService.addNewPlayer("Adolfo");

        Throwable exception = assertThrows(BowlingGameException.class, () -> {
            pinfallService.recordPlay(player, result1);
        });

        assertEquals(((BowlingGameException)exception)
                .getCode(), BowlingCodeException.INVALID_VALUE_RESULT.name());

        String result2 = "-1";

        exception = assertThrows(BowlingGameException.class, () -> {
            pinfallService.recordPlay(player, result2);
        });

        assertEquals(((BowlingGameException)exception)
                .getCode(), BowlingCodeException.INVALID_VALUE_RESULT.name());

        String result3 = "12";

        exception = assertThrows(BowlingGameException.class, () -> {
            pinfallService.recordPlay(player, result3);
        });

        assertEquals(((BowlingGameException)exception)
                .getCode(), BowlingCodeException.INVALID_VALUE_RESULT.name());

        String result4 = "";

        exception = assertThrows(BowlingGameException.class, () -> {
            pinfallService.recordPlay(player, result4);
        });

        assertEquals(((BowlingGameException)exception)
                .getCode(), BowlingCodeException.INVALID_VALUE_RESULT.name());
    }

}
