package com.jobsity.exercise.bowling;

import com.jobsity.exercise.bowling.container.BowlingGameFactory;
import com.jobsity.exercise.bowling.container.BowlingGameFactoryImpl;
import com.jobsity.exercise.bowling.exception.BowlingGameException;
import com.jobsity.exercise.bowling.model.BowlingGame;
import com.jobsity.exercise.bowling.service.game.BowlingGameServiceImpl;
import com.jobsity.exercise.bowling.service.game.GameService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Scanner;

@Configuration
@SpringBootApplication
public class BowlingApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(BowlingApplication.class, args);


		String successfullMsg = "The was read correctly. The score was written at %s" ;

		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter the file absolute path or 1 to stop the program");

		GameService gameService = (GameService) applicationContext.getBean("bowlingGameServiceImpl", GameService.class);

		while(scanner.hasNextLine()) {

			try {

				String path = scanner.nextLine().trim();

				if(path.trim().equalsIgnoreCase("1")) {
					System.exit(0);
				}
				String output = gameService.startGame(path);

				System.out.println(String.format(successfullMsg,  output));
				System.lineSeparator();
				System.out.println("Please enter the file absolute path or 1 to stop the program");
				gameService.resetGame();

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

	@Bean
	public BowlingGameFactory createGameFactory(){
		return new BowlingGameFactoryImpl();
	}

	@Bean
	public BowlingGame setGame(){
		return createGameFactory().createGame();
	}

}
