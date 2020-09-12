package com.jobsity.exercise.bowling.service.game;

import com.jobsity.exercise.bowling.BowlingApplication;
import com.jobsity.exercise.bowling.factory.BowlingGameFactory;
import com.jobsity.exercise.bowling.factory.BowlingGameFactoryImpl;
import com.jobsity.exercise.bowling.service.output.PinfallShowScoreService;
import com.jobsity.exercise.bowling.service.output.PinfallShowScoreServiceImpl;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.*;

import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


import com.jobsity.exercise.bowling.exception.BowlingCodeException;
import com.jobsity.exercise.bowling.exception.BowlingGameException;
import com.jobsity.exercise.bowling.model.BowlingGame;
import com.jobsity.exercise.bowling.model.Pinfall;
import com.jobsity.exercise.bowling.model.Player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class PinfallServiceTest {

    private Player player;
    @Autowired
    private PinfallService pinfallService;

    private BowlingGame game;

    @BeforeEach
    void prepareGame() throws BowlingGameException {
        player = new Player("Adolfo");
    }

    @Test
    public void addResultStrikeToFrame1() throws BowlingGameException {

        String result1 = "10";

        Pinfall pinfall = pinfallService.recordPlay(player, result1);

        assertEquals(result1, pinfall.getValue1());
        assertEquals("", pinfall.getValue2());
        assertTrue(pinfall.isStrike());
        assertTrue(pinfall.isClosed());
    }

    @Test
    public void addResultSpareToFrame1() throws BowlingGameException {

        String result1 = "4";
        String result2 = "6";

        pinfallService.recordPlay(player, result1);
        Pinfall pinfall = pinfallService.recordPlay(player, result2);

        assertEquals("4", pinfall.getValue1());
        assertEquals("6", pinfall.getValue2());
        assertTrue(pinfall.isSpare());
        assertTrue(pinfall.isClosed());
    }

    @Test
    public void addResultRegularToFrame1() throws BowlingGameException {

        String result1 = "4";
        String result2 = "5";

        pinfallService.recordPlay(player, result1);
        Pinfall pinfall = pinfallService.recordPlay(player, result2);

        assertEquals(result1, pinfall.getValue1());
        assertEquals(result2, pinfall.getValue2());
        assertTrue(pinfall.isRegular());
        assertTrue(pinfall.isClosed());
    }

    @Test
    public void addResultInvalidToFrame1() throws BowlingGameException {

        String result1 = "w";

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
