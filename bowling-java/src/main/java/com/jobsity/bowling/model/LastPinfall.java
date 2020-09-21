/**
 * 
 */
package com.jobsity.bowling.model;

import com.jobsity.bowling.exception.BowlingGameException;

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
	
	protected boolean isValueX(String value) {
		return value.equals("10");
	}
	
	@Override
	public String showPinfallValue() {
		
		String value1Temp = "";
		
		if(isValueX(getValue1())) {
			value1Temp = "X";
		}else {
			value1Temp = getValue1();
		} 
		
		String value2Temp = "";
		if(isValueX(getValue2())) {
			value1Temp = "X";
			
		}else if(getValue1Number() + getValue2Number() == 10 && getValue2Number() != 0) {
			value2Temp = "/";
		} else {
			value2Temp = getValue2();
		}
		
		
		String value3Temp = "";
		if(isValueX(getValue3())) {
			value1Temp = "X";
			
		}else if(getValue3Number() != 0 && ((getValue2Number() + getValue3Number() == 10 ) 
				  || (getValue1Number() + getValue2Number() + getValue3Number() == 10 ) )) {
			
			value2Temp = "/";
		} else {
			value3Temp = getValue3();
		}
		
		return showValue(value1Temp) + showValue(value2Temp) + showValue(value3Temp); 
	}
	
	public Integer getValue3Number() {
		return getValue(value3);
	}
	
}
