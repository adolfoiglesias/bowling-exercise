package com.jobsity.bowling;

import java.io.IOException;
import java.util.Scanner;

import com.jobsity.bowling.exception.BowlingGameException;
import com.jobsity.bowling.service.game.BowlingGameServiceImpl;
import com.jobsity.bowling.service.game.GameService;

//Test files : 
//input.txt
//input-jeff.txt
//input-john.txt

public class App {
	
	public static void main(String[] args) {
		
		
		String successfullMsg = "The was read correctly. The score was written at %s" ; 
				
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter the file absolute path or 1 to stop the program");
		
		while(scanner.hasNextLine()) {
		
			try {
				
				String path = scanner.nextLine().trim();
				
				if(path.trim().equalsIgnoreCase("1")) {
					System.exit(0);
				}
				
				GameService game = new BowlingGameServiceImpl();
				
				String output = game.startGame(path);
				
				System.out.println(String.format(successfullMsg,  output));
				
				System.exit(0);
				
			} catch (IOException | BowlingGameException e) {
				handleException(e);
			}
		}
		scanner.close();
	}
	
	
	private static void handleException(Exception e) {
		if(e instanceof IOException) {
			
			System.out.println("The absolute file path is incorrect or the current user does not have "
					+ "permission for reading it. "
					+ "Please check this and enter a right absolute file path");
			
		} else if(e instanceof BowlingGameException) {
			System.out.println(e.getMessage());
			
		}else {
			
			System.out.println("Unexpected error, please contact System Administrator");
			System.exit(0);
		}				
	}
	
}
