/**
 * 
 */
package com.jobsity.bowling.service.game;

import com.jobsity.bowling.exception.BowlingGameException;
import com.jobsity.bowling.model.Player;

/**
 * @author Adolfo Miguel Iglesias
 *
 */
public interface PlayerService {
	
	/**
	 * 
	 * @param playerName
	 * @return Add new player if not exist. Return the player
	 * @throws BowlingGameException
	 */
	public Player addNewPlayer(String playerName)  throws BowlingGameException;
	

}
