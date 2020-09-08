/**
 * 
 */
package com.jobsity.exercise.bowling.exception;

/**
 * @author Adolfo Miguel Iglesias
 *  <p>
 * 	Bowling Exception 
 * </p>
 */
public class BowlingGameException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String code;

	public BowlingGameException(String code) {
		super();
		this.code = code;
	}

	public BowlingGameException(String message, String code) {
		super(message);
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}	
	
	
	
}
