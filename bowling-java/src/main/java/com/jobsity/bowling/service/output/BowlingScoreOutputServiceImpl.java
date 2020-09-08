/**
 * 
 */
package com.jobsity.bowling.service.output;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.jobsity.bowling.exception.BowlingCodeException;
import com.jobsity.bowling.exception.BowlingGameException;
import com.jobsity.bowling.model.BowlingGame;

/**
 * @author Adolfo Miguel Iglesias
 *
 */
public class BowlingScoreOutputServiceImpl  implements ScoreOutputService {
	
	public static final  String OUTPUT_FILE_NAME = "output.txt";
	
	private String path;
	
	public void setPath(String path) {
		String newDir =  path == null ? "./" :  path + "/";
		this.path = newDir + OUTPUT_FILE_NAME;
	}
	
	@Override
	public String getPath() {
		return path;
	}


	@Override
	public String showScore(BowlingGame game) throws IOException, BowlingGameException {
		
		// first line 
		
		Boolean[] noWritteScore = new Boolean[1];
		noWritteScore[0] = false;
		
		Writer writer = new FileWriter(path);
		
		BufferedWriter bufferedWriter = new BufferedWriter(writer);
		
		
		final String header = "Frame" + "\t\t" + IntStream.range(0, 10)
													.mapToObj(i -> String.valueOf(i+1))
													.collect(Collectors.joining("   "));
		
		final String pinfallsLabel = "Pinfalls" + "\t";
		final String scoreLabel = "Score" + "\t\t";
		
		bufferedWriter.append(header); bufferedWriter.newLine();
		
		game.getPlayers().forEach(player -> {
			
			try {
				
				bufferedWriter.append(player.getPlayerName());
				bufferedWriter.newLine();
				
				bufferedWriter.append(pinfallsLabel);
				
				String pinfalls = Arrays.stream(player.getFrames())
							.map(frame -> frame.getPinfall().showPinfallValue())
							.collect(Collectors.joining(""));
				
				bufferedWriter.append(pinfalls);
				bufferedWriter.newLine();
				bufferedWriter.append(scoreLabel);
				
				
				String scores = Arrays.stream(player.getFrames())
							.map(frame ->  frame.showScoreValue())
							.collect(Collectors.joining(""));
				
				bufferedWriter.append(scores);
				bufferedWriter.newLine();
				
			} catch (IOException e) {
				noWritteScore[0] = true;
			} 
		});
		
		bufferedWriter.close();
		
		if(noWritteScore[0]) {
			throw new BowlingGameException("Error writing score at file", BowlingCodeException.NO_WRITTEN.name());
		}
		
		return  getPath();
	
	}

}
