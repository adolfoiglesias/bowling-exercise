/**
 * 
 */
package com.jobsity.exercise.bowling.service.game;

import com.jobsity.exercise.bowling.model.Player;

/**
 * @author Adolfo Miguel Iglesias
 *
 */
public interface ScoreService {
	
	/**
	 * Calculate the game score 
	 * @param player
	 */
	public void score( Player player) ;

}
