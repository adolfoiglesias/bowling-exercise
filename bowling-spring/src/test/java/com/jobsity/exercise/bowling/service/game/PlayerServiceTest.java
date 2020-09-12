package com.jobsity.exercise.bowling.service.game;

import com.jobsity.exercise.bowling.BowlingApplication;
import com.jobsity.exercise.bowling.factory.BowlingGameFactory;
import com.jobsity.exercise.bowling.factory.BowlingGameFactoryImpl;
import com.jobsity.exercise.bowling.exception.BowlingCodeException;
import com.jobsity.exercise.bowling.exception.BowlingGameException;
import com.jobsity.exercise.bowling.model.BowlingGame;
import com.jobsity.exercise.bowling.model.Player;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class PlayerServiceTest {

    @Autowired
    private PlayerService playerService;

    @MockBean
    private BowlingGameFactory gameFactory;
    private BowlingGame game;

    @BeforeEach
    void prepareGame() {
        Mockito.when(gameFactory.createGame()).thenReturn(new BowlingGame());
        game = gameFactory.createGame();
    }

    @Test
    @DisplayName("Adding player 2 times, only added 1 player. No repetead player")
    public void addPlayerValid() throws BowlingGameException {

        String playerName = "Adolfo";
        Player player1 = playerService.addNewPlayer(playerName, game);
        assertEquals(playerName, player1.getPlayerName());

        playerService.addNewPlayer(playerName, game);
        assertEquals(1, game.getPlayers().size());
    }

    @Test
    @DisplayName("Adding player with name invalid. Throw exception")
    public void addPlayerInValidName() throws BowlingGameException {

        final String playerName1 = "";
        Throwable exception = assertThrows(BowlingGameException.class, () -> {
            playerService.addNewPlayer(playerName1, game);
        });
        assertEquals(((BowlingGameException)exception)
                .getCode(), BowlingCodeException.INVALID_FORMAT.name());


        final String playerName2 = null;
        exception = assertThrows(BowlingGameException.class, () -> {
            playerService.addNewPlayer(playerName2, game);
        });
        assertEquals(((BowlingGameException)exception)
                .getCode(), BowlingCodeException.INVALID_FORMAT.name());

        final String playerName3 = "  ";
        exception = assertThrows(BowlingGameException.class, () -> {
            playerService.addNewPlayer(playerName3, game);
        });
        assertEquals(((BowlingGameException)exception)
                .getCode(), BowlingCodeException.INVALID_FORMAT.name());
    }
}
