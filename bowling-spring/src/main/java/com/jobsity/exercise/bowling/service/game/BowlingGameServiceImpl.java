/**
 * 
 */
package com.jobsity.exercise.bowling.service.game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

import com.jobsity.exercise.bowling.container.BowlingGameFactoryImpl;
import com.jobsity.exercise.bowling.container.BowlingGameFactory;
import com.jobsity.exercise.bowling.exception.BowlingCodeException;
import com.jobsity.exercise.bowling.exception.BowlingGameException;
import com.jobsity.exercise.bowling.model.BowlingGame;
import com.jobsity.exercise.bowling.model.ContainerGame;
import com.jobsity.exercise.bowling.model.Pinfall;
import com.jobsity.exercise.bowling.model.Player;
import com.jobsity.exercise.bowling.service.input.FileInputServiceImpl;
import com.jobsity.exercise.bowling.service.input.InputService;
import com.jobsity.exercise.bowling.service.output.BowlingScoreOutputServiceImpl;
import com.jobsity.exercise.bowling.service.output.ScoreOutputService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Adolfo Miguel iglesias
 * <p>
 * 	Class represents the implemented service of GameService interface
 * </p>s
 */
@Service
public class BowlingGameServiceImpl implements GameService {

	@Autowired
	private BowlingGameFactory bowlingGameFactory;

	@Autowired
	private PlayerService playerService;
	@Autowired
	private PinfallService pinfallService;
	@Autowired
	private ScoreService scoreService;
	@Autowired
	private ScoreOutputService outputService;
	@Autowired
	private InputService inputService;

	public BowlingGameServiceImpl() {
	}

	public BowlingGameServiceImpl(BowlingGameFactory bowlingGameFactory, PlayerService playerService, PinfallService pinfallService, ScoreService scoreService,
								  ScoreOutputService outputService, InputService inputService) {
		this.bowlingGameFactory = bowlingGameFactory;
		this.playerService = playerService;
		this.pinfallService = pinfallService;
		this.scoreService = scoreService;
		this.outputService = outputService;
		this.inputService = inputService;
	}

	@Override
	public String startGame(String path) throws FileNotFoundException, IOException, BowlingGameException {

		File file = inputService.getResource(path);

		BufferedReader gameFileReader = new BufferedReader(new FileReader(file));

		createGame();

		String line = null;
		int pos = 1;

		while ((line = gameFileReader.readLine()) != null) {

			String[] arrayLine = getResultByLine(line, pos);

			recordPlay(arrayLine[0], arrayLine[1]);

			pos++;
		}

		gameFileReader.close();

		outputService.setPath(file.getParent());
		return renderScore();
	}

	protected String[] getResultByLine(String line, int pos) throws BowlingGameException {

		/*
		 * if (line.trim().matches("\\w+\\t[\\d{1,2}|F]")) { throw new
		 * BowlingGameException("The row " + pos + " (" + line + ") is invalid",
		 * BowlingCodeException.INVALID_FORMAT.name()); }
		 */
		
		String[] arrayLine = line.split("\\t");
		return arrayLine;

	}

	@Override
	public void createGame() {
		BowlingGame game = bowlingGameFactory.createGame();
		ContainerGame.setGame(game);
	}

	@Override
	public void resetGame() {
		BowlingGame game = ContainerGame.getGame();
		if(game != null){
			game.setPlayers(new HashSet<>());
		}
	}

	protected void validate(Player player) throws BowlingGameException {
		if(player.getCurrentFrameNumber() == 10 && player.getCurrentFrame().getPinfall().isClosed()) {
			throw new BowlingGameException("The game does not support more result. Please enter a valid file path ", BowlingCodeException.INVALID_VALUE_RESULT.name());
		}
	}

	public void recordPlay(String playerName, String result) throws BowlingGameException {

		Player player = playerService.addNewPlayer(playerName);
		
		validate(player);

		Pinfall pinfall = pinfallService.recordPlay(player, result);

		scoreService.score(player);

		pinfallService.addCurrentFrameNumber(player, pinfall);
	}

	@Override
	public String renderScore() throws IOException, BowlingGameException {
		return outputService.showScore(ContainerGame.getGame());
	}




}
