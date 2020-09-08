/**
 * 
 */
package com.jobsity.exercise.bowling.service.game;

import com.jobsity.exercise.bowling.exception.BowlingGameException;
import com.jobsity.exercise.bowling.model.Player;

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
