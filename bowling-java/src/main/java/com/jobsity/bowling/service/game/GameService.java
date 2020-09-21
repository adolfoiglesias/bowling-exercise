/**
 * 
 */
package com.jobsity.bowling.service.game;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.jobsity.bowling.exception.BowlingGameException;

/**
 * @author Adolfo Miguel Iglesias 
 * <p>
 * 	Class represents the main methods of the system. 
 * </p>
 */
public interface GameService {
	
	/**
	 * Reset a game if not exist
	 */
	public void resetGame();

	
	/**
	 * Create new instance of a game if not exist
	 */
	public void createGame();
	
	/**
	 * Save a pinfall
	 * 
	 * @param playName
	 * @param pinfallResult
	 * @throws BowlingGameException
	 */
	public void recordPlay(String playName, String pinfallResult) throws BowlingGameException;
	
	/**
	 * 
	 * @param path
	 * @return Prepare all component to start a game 
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws BowlingGameException
	 */
	public String startGame(String path) throws FileNotFoundException, IOException, BowlingGameException;
	
	/**
	 * 
	 * @return Get the score file path 
	 * @throws IOException
	 * @throws BowlingGameException
	 */
	public String renderScore() throws IOException, BowlingGameException ;
	
}
