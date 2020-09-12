/**
 * 
 */
package com.jobsity.exercise.bowling.service.game;

import java.io.*;

import com.jobsity.exercise.bowling.factory.BowlingGameFactory;
import com.jobsity.exercise.bowling.exception.BowlingCodeException;
import com.jobsity.exercise.bowling.exception.BowlingGameException;
import com.jobsity.exercise.bowling.model.BowlingGame;
import com.jobsity.exercise.bowling.model.Pinfall;
import com.jobsity.exercise.bowling.model.Player;
import com.jobsity.exercise.bowling.service.input.InputService;
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

	protected BowlingGame game;
	protected BufferedWriter bufferedWriter;

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
	public void startGame(String path) throws FileNotFoundException, IOException, BowlingGameException {

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
		outputService.showScore(game);
	}

	protected String[] getResultByLine(String line, int pos) throws BowlingGameException {

		String regex = "^\\w+\\t(\\d|10|F)";

		if (!line.trim().matches(regex)) {
			throw new BowlingGameException("The row " + pos + " (" + line + ") is invalid",
					BowlingCodeException.INVALID_FORMAT.name());
		}

		String[] arrayLine = line.split("\\t");
		return arrayLine;
	}

	@Override
	public void createGame() {
		this.game = bowlingGameFactory.createGame();
	}

	@Override
	public BowlingGame getGame() {
		return game;
	}


	protected void validate(Player player) throws BowlingGameException {
		if(player.getCurrentFrameNumber() == 10 && player.getCurrentFrame().getPinfall().isClosed()) {
			throw new BowlingGameException("The game does not support more result. Please enter a valid file path ", BowlingCodeException.INVALID_VALUE_RESULT.name());
		}
	}

	public void recordPlay(String playerName, String result) throws BowlingGameException {

		Player player = playerService.addNewPlayer(playerName, game);
		validate(player);

		Pinfall pinfall = pinfallService.recordPlay(player, result);
		scoreService.score(player);

		pinfallService.addCurrentFrameNumber(player, pinfall);
	}


}
