/**
 * 
 */
package com.jobsity.exercise.bowling.service.game;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.jobsity.exercise.bowling.exception.BowlingGameException;
import com.jobsity.exercise.bowling.model.BowlingGame;

/**
 * @author Adolfo Miguel Iglesias 
 * <p>
 * 	Class represents the main methods of the system. 
 * </p>
 */
public interface GameService {

	/**
	 * Get the entity game
	 * @return
	 */
	public BowlingGame getGame();
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
	 * 	 * Prepare all component to execute a game
	 * @param path
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws BowlingGameException
	 */
	public void startGame(String path) throws FileNotFoundException, IOException, BowlingGameException;

}
