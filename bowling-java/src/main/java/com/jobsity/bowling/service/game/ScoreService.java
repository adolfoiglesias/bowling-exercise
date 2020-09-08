/**
 * 
 */
package com.jobsity.bowling.service.game;

import com.jobsity.bowling.model.Player;

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
