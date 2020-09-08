package com.jobsity.exercise.bowling.service.game;

import com.jobsity.exercise.bowling.exception.BowlingGameException;
import com.jobsity.exercise.bowling.model.Pinfall;
import com.jobsity.exercise.bowling.model.Player;

/**
 * @author Adolfo Miguel Iglesias
 *
 */

public interface PinfallService {
	
	/**
	 * 
	 * @param player
	 * @param pinfallResult
	 * @return Save a pinfall
	 * @throws BowlingGameException
	 */
	public Pinfall recordPlay(Player player, String pinfallResult) throws BowlingGameException ;
   
	/**
	 * Add a position at currentFrameNumber
	 * @param player
	 * @param pinfall
	 */
	public void addCurrentFrameNumber(Player player, Pinfall pinfall);
}
