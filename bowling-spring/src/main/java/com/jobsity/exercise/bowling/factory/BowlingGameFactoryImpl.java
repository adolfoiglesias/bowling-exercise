/**
 * 
 */
package com.jobsity.exercise.bowling.factory;

import com.jobsity.exercise.bowling.model.BowlingGame;
import org.springframework.stereotype.Service;

/**
 * @author Adolfo Miguel Iglesias
 *
 */
@Service
public class BowlingGameFactoryImpl implements BowlingGameFactory {
	public BowlingGame createGame() {
		return new BowlingGame();
	}
}
