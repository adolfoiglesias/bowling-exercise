/**
 * 
 */
package com.jobsity.exercise.bowling.model;

/**
 * @author Adolfo Miguel Iglesias
 *  <p>
 * 	Contaier that has the entity game
 * </p>
 */
public class ContainerGame {
	
	private static BowlingGame bowlingGame;
	
	public static BowlingGame getGame() {
		return bowlingGame;
	}
	
	public static void setGame(BowlingGame game) {
		bowlingGame = game;
	}
}
