package com.jobsity.exercise.bowling.service.game;

import com.jobsity.exercise.bowling.BowlingApplication;
import com.jobsity.exercise.bowling.container.BowlingGameFactory;
import com.jobsity.exercise.bowling.container.BowlingGameFactoryImpl;
import com.jobsity.exercise.bowling.exception.BowlingCodeException;
import com.jobsity.exercise.bowling.exception.BowlingGameException;
import com.jobsity.exercise.bowling.model.BowlingGame;
import com.jobsity.exercise.bowling.model.ContainerGame;
import com.jobsity.exercise.bowling.model.Player;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes =  BowlingApplication.class)
public class PlayerServiceTest {

    @InjectMocks
    private static PlayerService playerService = new PlayerServiceImpl();

    @BeforeEach
    void prepareGame() {
        BowlingGame game =  BowlingGame.createGame();
        ContainerGame.setGame(game);
    }

    @AfterEach
    void restartGame() {
        BowlingGame game = ContainerGame.getGame();
        Set<Player> players = new HashSet<>();
        game.setPlayers(players);
    }

    @Test
    @DisplayName("Adding player 2 times, only added 1 player. No repetead player")
    public void addPlayerValid() throws BowlingGameException {

        String playerName = "Adolfo";
        Player player1 = playerService.addNewPlayer(playerName);
        assertEquals(playerName, player1.getPlayerName());

        playerService.addNewPlayer(playerName);
        assertEquals(1, ContainerGame.getGame().getPlayers().size());
    }

    @Test
    @DisplayName("Adding player with name invalid. Throw exception")
    public void addPlayerInValidName() throws BowlingGameException {

        final String playerName1 = "";

        Throwable exception = assertThrows(BowlingGameException.class, () -> {
            playerService.addNewPlayer(playerName1);
        });
        assertEquals(((BowlingGameException)exception)
                .getCode(), BowlingCodeException.INVALID_FORMAT.name());

        final String playerName2 = null;

        exception = assertThrows(BowlingGameException.class, () -> {
            playerService.addNewPlayer(playerName2);
        });
        assertEquals(((BowlingGameException)exception)
                .getCode(), BowlingCodeException.INVALID_FORMAT.name());

        final String playerName3 = "  ";

        exception = assertThrows(BowlingGameException.class, () -> {
            playerService.addNewPlayer(playerName3);
        });
        assertEquals(((BowlingGameException)exception)
                .getCode(), BowlingCodeException.INVALID_FORMAT.name());
    }
}
