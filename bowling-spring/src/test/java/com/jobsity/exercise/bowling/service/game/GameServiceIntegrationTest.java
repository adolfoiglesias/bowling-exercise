package com.jobsity.exercise.bowling.service.game;

import com.jobsity.exercise.bowling.BowlingApplication;
import com.jobsity.exercise.bowling.container.BowlingGameFactory;
import com.jobsity.exercise.bowling.container.BowlingGameFactoryImpl;
import com.jobsity.exercise.bowling.exception.BowlingCodeException;
import com.jobsity.exercise.bowling.exception.BowlingGameException;
import com.jobsity.exercise.bowling.model.BowlingGame;
import com.jobsity.exercise.bowling.model.ContainerGame;
import com.jobsity.exercise.bowling.model.Pinfall;
import com.jobsity.exercise.bowling.model.Player;
import com.jobsity.exercise.bowling.service.input.FileInputServiceImpl;
import com.jobsity.exercise.bowling.service.input.InputService;
import com.jobsity.exercise.bowling.service.output.BowlingScoreOutputServiceImpl;
import com.jobsity.exercise.bowling.service.output.ScoreOutputService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@SpringBootTest(classes =  BowlingApplication.class)
public class GameServiceIntegrationTest {

    final static String PATH_BASE = "resources/";
    static String GAME_MULTI_PLAYER = PATH_BASE + "input.txt";

    static String PERFECT_GAME_PATH = PATH_BASE +  "input-perfect.txt";
    static String GAME_ZERO_PATH = PATH_BASE + "input-zero-game.txt";
    static String INVALID_GAME_RESULT_PATH = PATH_BASE + "input-invalid-result-game.txt";
    static String DIRECTORY_PATH = PATH_BASE + "./";

    static String OUTPUT_FILE = "resources/output.txt";

    static GameService gameService = new BowlingGameServiceImpl();


    @BeforeAll
    static void setUp(){
        BowlingGameFactory gameFactory = new BowlingGameFactoryImpl();
        InputService inputService = new FileInputServiceImpl();
        PlayerService playerService = new PlayerServiceImpl();
        PinfallService pinfallService = new PinfallServiceImpl();
        ScoreService scoreService = new ScoreServiceImpl();
        ScoreOutputService outputService = new BowlingScoreOutputServiceImpl();

        gameService = new BowlingGameServiceImpl(gameFactory, playerService, pinfallService, scoreService,
                outputService, inputService);
    }


    @AfterEach
    void restartGame() {
        BowlingGame game = ContainerGame.getGame();
        Set<Player> players = new HashSet<>();
        game.setPlayers(players);
    }


    @Test
    public void setPerfectGame() throws  IOException, BowlingGameException {
        String output = gameService.startGame(PERFECT_GAME_PATH);

        Player player = ContainerGame.getGame().getPlayers().iterator().next();

        assertEquals(OUTPUT_FILE, output);
        assertEquals(300, player.getFinalScore());
    }

    @Test
    public void setGameScoreZero() throws  IOException, BowlingGameException {

        String output = gameService.startGame(GAME_ZERO_PATH);
        Player player = ContainerGame.getGame().getPlayers().iterator().next();

        assertEquals(OUTPUT_FILE, output);
        assertEquals(0, player.getFinalScore());
    }

    @Test
    public void setGameScoreMultiplayer() throws IOException, BowlingGameException{
        String output = gameService.startGame(GAME_MULTI_PLAYER);

        assertEquals(OUTPUT_FILE, output);

        ContainerGame.getGame()
                .getPlayers()
                .forEach(player -> {
                    if(player.getPlayerName().equalsIgnoreCase("Jeff")) {
                        assertEquals(167,player.getFinalScore());
                    } else if(player.getPlayerName().equalsIgnoreCase("Jeff")) {
                        assertEquals(151,player.getFinalScore());
                    }
                });
    }

    @Test
    public void setGameWithInvaliResult() throws IOException, BowlingGameException {

        Throwable exception = assertThrows(BowlingGameException.class, () -> {
            gameService.startGame(INVALID_GAME_RESULT_PATH);
        });
        assertEquals(((BowlingGameException)exception)
                .getCode(), BowlingCodeException.INVALID_VALUE_RESULT.name());

    }


    @Test
    public void setInvalidFilePath() throws IOException, BowlingGameException {

        Throwable exception = assertThrows(BowlingGameException.class, () -> {
            gameService.startGame("");
        });
        assertEquals(((BowlingGameException)exception)
                .getCode(), BowlingCodeException.NO_FILE.name());

        exception = assertThrows(BowlingGameException.class, () -> {
            gameService.startGame("file");
        });
        assertEquals(((BowlingGameException)exception)
                .getCode(), BowlingCodeException.NO_FILE.name());

        exception = assertThrows(BowlingGameException.class, () -> {
            gameService.startGame(DIRECTORY_PATH);
        });
        assertEquals(((BowlingGameException)exception)
                .getCode(), BowlingCodeException.NO_FILE.name());
    }
}
