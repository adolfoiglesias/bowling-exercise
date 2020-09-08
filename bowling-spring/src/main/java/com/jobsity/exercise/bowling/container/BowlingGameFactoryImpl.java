/**
 * 
 */
package com.jobsity.exercise.bowling.container;

import com.jobsity.exercise.bowling.model.BowlingGame;

/**
 * @author Adolfo Miguel Iglesias
 *
 */
public class BowlingGameFactoryImpl implements BowlingGameFactory {
	
	public BowlingGame createGame() {
		return BowlingGame.createGame();
	}
}
