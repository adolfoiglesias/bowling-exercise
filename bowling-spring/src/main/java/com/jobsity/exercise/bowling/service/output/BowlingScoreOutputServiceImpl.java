/**
 * 
 */
package com.jobsity.exercise.bowling.service.output;

import java.io.*;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.jobsity.exercise.bowling.exception.BowlingCodeException;
import com.jobsity.exercise.bowling.exception.BowlingGameException;
import com.jobsity.exercise.bowling.model.BowlingGame;
import com.jobsity.exercise.bowling.model.LastPinfall;
import com.jobsity.exercise.bowling.model.Pinfall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author Adolfo Miguel Iglesias
 *
 */
@Service
public class BowlingScoreOutputServiceImpl  implements ScoreOutputService {

	@Autowired
	@Qualifier("pinfallShowScoreService")
	private PinfallShowScoreService pinfallShowScoreService;

	@Autowired
	@Qualifier("lastPinfallShowScoreService")
	private PinfallShowScoreService lastPinfallShowScoreService;

	@Override
	public void showScore(BowlingGame game) throws IOException, BowlingGameException {

		final String header = "Frame" + "\t\t" + IntStream.range(0, 10)
				.mapToObj(i -> String.valueOf(i+1))
				//											.collect(Collectors.joining("   "));
				.collect(Collectors.joining("\t\t"));

		final String pinfallsLabel = "Pinfalls" + "\t";
		final String scoreLabel = "Score" + "\t\t";

		System.out.println(header);

		game.getPlayers().forEach(player -> {

			System.out.println(player.getPlayerName());
			System.out.print(pinfallsLabel);

			String pinfalls = Arrays.stream(player.getFrames())
					.map(frame -> {
						if(frame.getPinfall() instanceof LastPinfall){
							return lastPinfallShowScoreService.showPinfallValue(frame.getPinfall());
						}else {
							return pinfallShowScoreService.showPinfallValue(frame.getPinfall());
						}
					})
					.collect(Collectors.joining(""));
			System.out.println(pinfalls);
			System.out.print(scoreLabel);

			String scores = Arrays.stream(player.getFrames())
					.map(frame ->  frame.showScoreValue())
					.collect(Collectors.joining("\t\t"));

			System.out.println(scores);
		});

	}

}
