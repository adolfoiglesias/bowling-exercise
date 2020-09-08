/**
 * 
 */
package com.jobsity.exercise.bowling.container;

import com.jobsity.exercise.bowling.model.BowlingGame;

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
