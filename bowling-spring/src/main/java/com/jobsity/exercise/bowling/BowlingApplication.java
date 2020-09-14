package com.jobsity.exercise.bowling;

import com.jobsity.exercise.bowling.exception.BowlingGameException;
import com.jobsity.exercise.bowling.service.game.GameService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

@Configuration
@SpringBootApplication
public class BowlingApplication {

	public static void main(String[] args) {
		if(args.length != 1){
			System.out.println("Please must enter a score path file");
			System.exit(0);
		}

		String path = args[0];
		ConfigurableApplicationContext applicationContext = SpringApplication.run(BowlingApplication.class, args);
		GameService gameService = (GameService) applicationContext.getBean("bowlingGameServiceImpl", GameService.class);

		try{
			gameService.startGame(path);
			System.exit(0);
		} catch (IOException | BowlingGameException e) {
			handleException(e);
			System.exit(0);
		}

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
		}
	}

}
