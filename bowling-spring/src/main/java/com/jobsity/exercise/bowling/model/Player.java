/**
 * 
 */
package com.jobsity.exercise.bowling.model;

/**
 * @author Adolfo Miguel Iglesias
 * <p>
 * 	Class represents the player of the game
 * </p>
 */
public class Player{

	private Frame[] frames;
	private String playerName;
	private Integer finalScore;
	private Integer currentFrameNumber;
	private Integer currentScoreFrame;
	
	
	public Player(String playerName) {
		super();
		
		this.frames = new Frame[10];
		this.frames[0] = new Frame(1, new Pinfall(), true);
		for (int i = 1; i < 9; i++) {
			this.frames[i] = new Frame(i+1, new Pinfall(), false);
		}
		this.frames[9] = new Frame(10, new LastPinfall(), false);
		
		this.playerName = playerName;
		this.currentFrameNumber = 1;
		this.currentScoreFrame = 1;
		this.finalScore = 0;
	}
	
	public Integer getFinalScore() {
		return finalScore;
	}

	public Frame getCurrentFrame() {
		return this.frames[this.currentFrameNumber-1];
	}


	public void setFinalScore(Integer finalScore) {
		this.finalScore = finalScore;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public Frame[] getFrames() {
		return frames;
	}

	public void setFrames(Frame[] frames) {
		this.frames = frames;
	}

	public Integer getCurrentFrameNumber() {
		return currentFrameNumber;
	}

	public void setCurrentFrameNumber(Integer currentFrameNumber) {
		this.currentFrameNumber = currentFrameNumber;
	}
	
	

	public Integer getCurrentScoreFrame() {
		return currentScoreFrame;
	}


	public void addPositionCurrentScoreFrame() {
		this.currentScoreFrame++;
	}
	
	public void setCurrentScoreFrame(Integer currentScoreFrame) {
		this.currentScoreFrame = currentScoreFrame;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		
		if(this == obj) {
			return true;
		}
		
		if (getClass() != obj.getClass()) {
			return false;
		}
	        
	    Player p = (Player) obj;
	    // field comparison
	    return this.playerName.equalsIgnoreCase(p.playerName);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
	    int result = 1;
	    result = prime * result + ((playerName == null) ? 0 : playerName.hashCode());
	    return result;
	}
	
	

}
