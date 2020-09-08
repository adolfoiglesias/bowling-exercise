/**
 * 
 */
package com.jobsity.exercise.bowling.service.game;

import com.jobsity.exercise.bowling.model.Frame;
import com.jobsity.exercise.bowling.model.LastPinfall;
import com.jobsity.exercise.bowling.model.Pinfall;
import com.jobsity.exercise.bowling.model.Player;
import org.springframework.stereotype.Service;

/**
 * @author Adolfo Miguel Iglesias
 *
 */
@Service
public class ScoreServiceImpl implements ScoreService {

	@Override
	public void score(Player player) {
		Frame frame = player.getCurrentFrame();
		Pinfall pinfall = frame.getPinfall();

		if (pinfall instanceof LastPinfall) {
			scoreInternalLast(player);
		} else {
			scoreInternal(player);
		}
	}

	protected void scoreInternal(Player player) {

		Frame[] frames = player.getFrames();

		Integer currentFrameNumber = player.getCurrentFrameNumber();
		Integer currentScoreFrameNumber = player.getCurrentScoreFrame();

		Frame currentScoreFrame = frames[currentScoreFrameNumber - 1];

		Pinfall pinfall = currentScoreFrame.getPinfall();

		if (pinfall.isClosed() && pinfall.isRegular()) {

			int subtotal = player.getFinalScore() + pinfall.getScore();
			player.setFinalScore(subtotal);
			currentScoreFrame.setScore(subtotal);
			player.addPositionCurrentScoreFrame();

		} else if (pinfall.isSpare() && currentScoreFrameNumber != currentFrameNumber) {

			int subTotal = player.getFinalScore() + pinfall.getScore()
					+ frames[currentFrameNumber - 1].getPinfall().getValue1Number();
			player.setFinalScore(subTotal);
			currentScoreFrame.setScore(subTotal);
			player.addPositionCurrentScoreFrame();

		} else if (pinfall.isStrike() && currentScoreFrameNumber != currentFrameNumber) {

			if (currentFrameNumber - currentScoreFrameNumber == 2) {

				int subTotal = player.getFinalScore() + pinfall.getScore()
								+ frames[currentFrameNumber - 2].getPinfall().getScore()		
								+ frames[currentFrameNumber - 1].getPinfall().getValue1Number();
				
				player.setFinalScore(subTotal);
				currentScoreFrame.setScore(subTotal);
				player.addPositionCurrentScoreFrame();

			} else if (frames[currentFrameNumber - 1].getPinfall().isClosed() 
					&& !frames[currentFrameNumber - 1].getPinfall().isStrike()) {
				
				int subTotal = player.getFinalScore() + pinfall.getScore()
						+ frames[currentFrameNumber - 1].getPinfall().getScore();
				
				player.setFinalScore(subTotal);
				currentScoreFrame.setScore(subTotal);
				player.addPositionCurrentScoreFrame();
				
			}
		}

	}

	protected void scoreInternalLast(Player player) {
		
		Frame[] frames = player.getFrames();

		Integer currentFrameNumber = player.getCurrentFrameNumber();
		Integer currentScoreFrameNumber = player.getCurrentScoreFrame();

		Frame currentScoreFrame = frames[currentScoreFrameNumber - 1];

		Pinfall pinfall = currentScoreFrame.getPinfall();
		
		if (pinfall.isClosed() && pinfall.isRegular()) {
			
			int subTotal = player.getFinalScore() + pinfall.getScore();
			player.setFinalScore(subTotal);
			currentScoreFrame.setScore(subTotal);
			
		} else if(currentScoreFrameNumber != currentFrameNumber ) {
			
			if (currentFrameNumber - currentScoreFrameNumber == 2) {

				int subTotal = player.getFinalScore() + pinfall.getScore()
								+ frames[currentFrameNumber - 2].getPinfall().getScore()		
								+ frames[currentFrameNumber - 1].getPinfall().getValue1Number();
				
				player.setFinalScore(subTotal);
				currentScoreFrame.setScore(subTotal);
				player.addPositionCurrentScoreFrame();

			}else {
				
				String result2 = frames[currentFrameNumber - 1].getPinfall().getValue2();
				
				if(!result2.isBlank()) {
					
					int subTotal = player.getFinalScore() + pinfall.getScore()
						+ frames[currentFrameNumber - 1].getPinfall().getValue1Number()
						+ frames[currentFrameNumber - 1].getPinfall().getValue2Number();
						
					player.setFinalScore(subTotal);
					currentScoreFrame.setScore(subTotal);
					player.addPositionCurrentScoreFrame();
				}
			}
		}
	}

}
