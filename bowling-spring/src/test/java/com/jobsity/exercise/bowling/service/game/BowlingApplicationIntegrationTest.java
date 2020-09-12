package com.jobsity.exercise.bowling.service.game;

import com.jobsity.exercise.bowling.exception.BowlingCodeException;
import com.jobsity.exercise.bowling.exception.BowlingGameException;
import com.jobsity.exercise.bowling.model.Player;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class BowlingApplicationIntegrationTest {

    final static String PATH_BASE = "resources/";
    final static String GAME_MULTI_PLAYER = PATH_BASE + "input.txt";

    final static String PERFECT_GAME_PATH = PATH_BASE +  "input-perfect.txt";
    final static String GAME_ZERO_PATH = PATH_BASE + "input-zero-game.txt";
    final static String INVALID_GAME_RESULT_PATH = PATH_BASE + "input-invalid-result-game.txt";
    final static String INVALID_FORMAT_FILE_PATH = PATH_BASE + "input-invalid-format-file-game.txt";
    final static String DIRECTORY_PATH = PATH_BASE + "./";

    @Autowired
    ApplicationContext context;

    @Test
    public void startPerfectGame() throws  IOException, BowlingGameException {
        GameService gameService = (GameService) context.getBean("bowlingGameServiceImpl", GameService.class);

        gameService.startGame(PERFECT_GAME_PATH);
        Player player = gameService.getGame().getPlayers().iterator().next();

        assertEquals(300, player.getFinalScore());
    }

    @Test
    public void startGameScoreZero() throws  IOException, BowlingGameException {
        GameService gameService = (GameService) context.getBean("bowlingGameServiceImpl", GameService.class);

        gameService.startGame(GAME_ZERO_PATH);
        Player player = gameService.getGame().getPlayers().iterator().next();

        assertEquals(0, player.getFinalScore());
    }

    @Test
    public void startGameScoreMultiplayer() throws IOException, BowlingGameException{
        GameService gameService = (GameService) context.getBean("bowlingGameServiceImpl", GameService.class);

        gameService.startGame(GAME_MULTI_PLAYER);

        gameService.getGame()
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
    public void startGameWithInvalidResult() throws IOException, BowlingGameException {
        GameService gameService = (GameService) context.getBean("bowlingGameServiceImpl", GameService.class);

        Throwable exception = assertThrows(BowlingGameException.class, () -> {
            gameService.startGame(INVALID_GAME_RESULT_PATH);
        });
        assertEquals(BowlingCodeException.INVALID_VALUE_RESULT.name(), ((BowlingGameException)exception)
                .getCode());
    }

    @Test
    public void startGameWithInvalidFormatFile() throws IOException, BowlingGameException {
        GameService gameService = (GameService) context.getBean("bowlingGameServiceImpl", GameService.class);

        Throwable exception = assertThrows(BowlingGameException.class, () -> {
            gameService.startGame(INVALID_FORMAT_FILE_PATH);
        });
        assertEquals(BowlingCodeException.INVALID_FORMAT.name(), ((BowlingGameException)exception)
                .getCode());
    }

    @Test
    public void startGameInvalidFilePath() throws IOException, BowlingGameException {
        GameService gameService = (GameService) context.getBean("bowlingGameServiceImpl", GameService.class);

        Throwable exception = assertThrows(BowlingGameException.class, () -> {
            gameService.startGame("");
        });
        assertEquals(BowlingCodeException.NO_FILE.name(), ((BowlingGameException)exception)
                .getCode());

        exception = assertThrows(BowlingGameException.class, () -> {
            gameService.startGame("file");
        });
        assertEquals(BowlingCodeException.NO_FILE.name(), ((BowlingGameException)exception)
                .getCode());

        exception = assertThrows(BowlingGameException.class, () -> {
            gameService.startGame(DIRECTORY_PATH);
        });
        assertEquals(BowlingCodeException.NO_FILE.name(), ((BowlingGameException)exception)
                .getCode());
    }

}
