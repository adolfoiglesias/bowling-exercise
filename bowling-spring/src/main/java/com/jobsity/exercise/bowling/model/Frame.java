/**
 * 
 */
package com.jobsity.exercise.bowling.model;

/**
 * @author Adolfo Miguel Iglesias
 *  <p>
 * 	Entity represents frame of bowling game. It has number frame , pinfall and score
 * </p>
 */
public class Frame {
	
	private Integer number;
	private Pinfall pinfall;
	private Integer score;
	
	
	public Frame() {
		super();
	}

	public Frame(Integer number, Pinfall pinfall, boolean current) {
		super();
		this.number = number;
		this.pinfall = pinfall;
		this.score = -1;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Pinfall getPinfall() {
		return pinfall;
	}

	public void setPinfall(Pinfall pinfall) {
		this.pinfall = pinfall;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}
	
	public String showScoreValue() {
		return showValue(String.valueOf(getScore()));
	}
	
	private String showValue(String value) {
		
		if(value.length() == 1) {
			return value + "   ";	
		} else if(value.length() == 2){
			// length = 2
			return value + "  ";
		} else {
			// length = 2
			return value + " ";
		}
	}
	
}
