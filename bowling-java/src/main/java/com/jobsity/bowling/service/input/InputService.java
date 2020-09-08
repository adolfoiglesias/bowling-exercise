/**
 * 
 */
package com.jobsity.bowling.service.input;

import java.io.File;

import com.jobsity.bowling.exception.BowlingGameException;

/**
 * @author Adolfo Miguel Iglesias
 *
 */
public interface InputService {
	
	/**
	 * 
	 * @param path
	 * @return Return input file if it is valid
	 * @throws BowlingGameException
	 */
	public File getResource(String path) throws BowlingGameException;

}
