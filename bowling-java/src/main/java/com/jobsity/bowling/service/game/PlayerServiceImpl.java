/**
 * 
 */
package com.jobsity.bowling.service.game;

import java.util.Optional;
import java.util.Set;

import com.jobsity.bowling.exception.BowlingCodeException;
import com.jobsity.bowling.exception.BowlingGameException;
import com.jobsity.bowling.model.BowlingGame;
import com.jobsity.bowling.model.ContainerGame;
import com.jobsity.bowling.model.Player;

/**
 * @author Adolfo Miguel Iglesias
 *
 */
public class PlayerServiceImpl implements PlayerService {

	public Player addNewPlayer(String playerName) throws BowlingGameException {
		
		if(playerName == null || playerName.isBlank()) {
			throw new BowlingGameException("playerName can not be empty", BowlingCodeException.INVALID_FORMAT.name());
		}
		
		BowlingGame game = ContainerGame.getGame();
		Player player = createPlayer(playerName);
		
		boolean added = game.addPlayer(player);
		
		if(added) {
			return player;
		} else {
			Set<Player> players = game.getPlayers();

			Optional<Player> playerOpt =  players.stream()
					.filter(p -> p.getPlayerName().equalsIgnoreCase(playerName))
					.findFirst();
			
			return playerOpt.orElseThrow(()-> new BowlingGameException("Player not found ", 
					BowlingCodeException.PLAYER_NAME_EMPTY.name()));
		}
	}
	
	protected Player createPlayer(String playerName) throws BowlingGameException {
		// validate playerName not null nor empty. Throw exception in that case.
		if (playerName == null || playerName.isEmpty()) {
			throw new BowlingGameException("Player name can not be empty", BowlingCodeException.PLAYER_NAME_EMPTY.name());
		}
		return new Player(playerName);
	}
}
