/**
 * 
 */
package com.jobsity.bowling.container;

import com.jobsity.bowling.model.BowlingGame;

/**
 * @author Adolfo Miguel Iglesias
 *
 */
public class BowlingGameFactoryImpl implements BowlingGameFactory {
	
	public BowlingGame createGame() {
		return BowlingGame.createGame();
	}
}
