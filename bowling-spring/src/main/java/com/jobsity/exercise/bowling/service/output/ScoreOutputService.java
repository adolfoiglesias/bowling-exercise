/**
 * 
 */
package com.jobsity.exercise.bowling.service.output;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;

import com.jobsity.exercise.bowling.exception.BowlingGameException;
import com.jobsity.exercise.bowling.model.BowlingGame;

/**
 * @author Adolfo Miguel Iglesias
 *
 */
public interface ScoreOutputService {
	
	/**
	 * 
	 * Show the game score
	 * @param game
	 * @throws IOException
	 * @throws BowlingGameException
	 */
	public void showScore(BowlingGame game) throws IOException, BowlingGameException;

}
