/**
 * 
 */
package com.jobsity.exercise.bowling.model;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Adolfo Miguel Iglesias
 * <p>
 * 	Represent the Game of the program. It has a players Set  
 * </p>
 */
public class BowlingGame {
	
	private Set<Player> players;

	public BowlingGame() {
		super();
		players = new HashSet<Player>();
	}

	public Set<Player> getPlayers() {
		return players;
	}

	public void setPlayers(Set<Player> players) {
		this.players = players;
	}

	public boolean addPlayer(Player e) {
		return players.add(e);
	}

}
