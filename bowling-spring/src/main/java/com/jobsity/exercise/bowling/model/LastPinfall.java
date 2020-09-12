/**
 * 
 */
package com.jobsity.exercise.bowling.model;

import com.jobsity.exercise.bowling.exception.BowlingGameException;

/**
 * @author Adolfo Miguel Iglesias
 * <p>
 * 	Class represents the last results of a frame
 * </p>
 */
public class LastPinfall extends Pinfall {

	private String value3;

	public LastPinfall() {
		super();
		value3 = "";
	}

	public String getValue3() {
		return value3;
	}

	@Override
	public boolean addResult(String result) throws BowlingGameException {

		validateResult(result);

		result = result.trim();

		if(value1.isBlank()) {
			value1 = result;
			pinfallType = PinfallType.REGULAR;

		}else if(value2.isBlank()) {

			value2 = result;
			pinfallType = PinfallType.REGULAR;

		}else {
			value3 = result;
			pinfallType = PinfallType.REGULAR;
			closed = true;
		}
		return closed;
	}

	public Integer getScore() {
		return getValue1Number() + getValue2Number() + getValue3Number();
	}

	public boolean isValueX(String value) {
		return value.equals("10");
	}

	public Integer getValue3Number() {
		return getValue(value3);
	}
	
}
