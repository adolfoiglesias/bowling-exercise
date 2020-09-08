/**
 * 
 */
package com.jobsity.bowling.model;

import com.jobsity.bowling.exception.BowlingGameException;

/**
 * @author Adolfo Miguel Iglesias
 * <p>
 * 	Interface has methods must be implemented for each Pinfall Class
 * </p>
 */
public interface PinfallInterface {
	
	public boolean addResult(String result) throws BowlingGameException;
	
	public String showPinfallValue();

}
