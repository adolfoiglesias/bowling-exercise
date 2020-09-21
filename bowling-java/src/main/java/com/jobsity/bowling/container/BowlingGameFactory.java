/**
 * 
 */
package com.jobsity.bowling.container;

import com.jobsity.bowling.model.BowlingGame;

/**
 * @author Adolfo Miguel Iglesias
 *
 */
public interface BowlingGameFactory {
	
	/**
	 * Create a game
	 * @return
	 */
	public BowlingGame createGame();

}
