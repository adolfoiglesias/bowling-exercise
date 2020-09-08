/**
 * 
 */
package com.jobsity.bowling.service.output;

import java.io.IOException;

import com.jobsity.bowling.exception.BowlingGameException;
import com.jobsity.bowling.model.BowlingGame;

/**
 * @author Adolfo Miguel Iglesias
 *
 */
public interface ScoreOutputService {
	
	/**
	 * 
	 * @param game
	 * @return Save the game score  
	 * @throws IOException
	 * @throws BowlingGameException
	 */
	public String showScore(BowlingGame game) throws IOException, BowlingGameException;
	
	
	public void setPath(String path);
	
	/**
	 * 
	 * @return Return the file path with score
	 */
	public String getPath();

}
