/**
 * 
 */
package com.jobsity.service.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.jobsity.bowling.container.BowlingGameFactoryImpl;
import com.jobsity.bowling.container.BowlingGameFactory;
import com.jobsity.bowling.exception.BowlingCodeException;
import com.jobsity.bowling.exception.BowlingGameException;
import com.jobsity.bowling.model.BowlingGame;
import com.jobsity.bowling.model.ContainerGame;
import com.jobsity.bowling.model.Player;
import com.jobsity.bowling.service.game.PlayerService;
import com.jobsity.bowling.service.game.PlayerServiceImpl;

/**
 * @author Adolfo Miguel Iglesias
 *
 */
public class PlayerServiceTest   {
	
	private static PlayerService playerService; 
	private static BowlingGameFactory bowlingGameFactory;
	
	
	@BeforeAll
	static void setup() {
		playerService = new PlayerServiceImpl();
		bowlingGameFactory = new BowlingGameFactoryImpl();
	}
	
	@BeforeEach
	void prepareGame() {
		BowlingGame game = bowlingGameFactory.createGame();
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
