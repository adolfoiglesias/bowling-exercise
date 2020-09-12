/**
 * 
 */
package com.jobsity.exercise.bowling.model;

import com.jobsity.exercise.bowling.exception.BowlingCodeException;
import com.jobsity.exercise.bowling.exception.BowlingGameException;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Adolfo Miguel Iglesias
 * <p>
 * 	Class represents the results of a frame
 * </p>
 */
public class Pinfall {

	protected String value1;
	protected String value2;
	protected boolean closed;

	protected PinfallType pinfallType;

	public Pinfall() {
		super();
		value1 = "";
		value2 = "";
	}

	public boolean addResult(String result) throws BowlingGameException {

		validateResult(result);
		result = result.trim();

		if(result.equalsIgnoreCase("10")) {

			pinfallType = PinfallType.STRIKE;
			value1 = result;
			closed = true;

		} else {

			if(value1.isBlank()) {
				value1 = result;

			} else {

				value2 = result;

				if(isSpareInternal()) {
					pinfallType = PinfallType.SPARE;
				}else {
					pinfallType = PinfallType.REGULAR;
				}
				closed = true;
			}
		}
		return closed;
	}

	protected void validateResult(String result) throws BowlingGameException {
		if(result == null || result.isBlank()) {
			throw new BowlingGameException("Invalid result . It can not be empty", BowlingCodeException.INVALID_VALUE_RESULT.name());
		}
		result = result.trim();
		if(StringUtils.isNumeric(result)) {

			if(Integer.valueOf(result) < 0 || Integer.valueOf(result) > 10) {
				throw new BowlingGameException("Invalid result. It can not be < 0 or > 10 ", BowlingCodeException.INVALID_VALUE_RESULT.name());
			}

			try{
				Integer.parseInt(result);
			} catch (NumberFormatException ex){
				throw new BowlingGameException("Invalid result. It can not be a decimal number ", BowlingCodeException.INVALID_VALUE_RESULT.name());
			}

		} else {
			if(!result.equalsIgnoreCase("F")) {
				throw new BowlingGameException("Invalid result. It must be number >= 0 and <= 10 or the letter 'F'", BowlingCodeException.INVALID_VALUE_RESULT.name());
			}
		}
	}

	private boolean isSpareInternal() {

		if (StringUtils.isNumeric(value1) && StringUtils.isNumeric(value2)) {
			return (Integer.valueOf(value1) + Integer.valueOf(value2)) == 10;
		} else {
			return false;
		}
	}

	public Integer getScore() {

		if (isStrike() || isSpare()) {
			return 10;
		} else {

			if (StringUtils.isNumeric(getValue1()) && StringUtils.isNumeric(getValue2())) {
				return getValue1Number() + getValue2Number();

			} else if (StringUtils.isNumeric(getValue1())) {
				return getValue1Number();
			} else {
				return getValue2Number();
			}
		}
	}

	public boolean isStrike() {
		return getPinfallType() == PinfallType.STRIKE;
	}

	public boolean isSpare() {
		return getPinfallType() == PinfallType.SPARE;
	}

	public boolean isRegular() {
		return getPinfallType() == PinfallType.REGULAR;
	}

	public Integer getValue1Number() {
		return getValue(getValue1());
	}

	public Integer getValue2Number() {
		return getValue(getValue2());
	}

	protected Integer getValue(String value) {
		return StringUtils.isNumeric(value) ? Integer.valueOf(value) : 0;
	}

	public String getValue1() {
		return value1;
	}

	public String getValue2() {
		return value2;
	}

	public boolean isClosed() {
		return closed;
	}

	public PinfallType getPinfallType() {
		return pinfallType;
	}
}
