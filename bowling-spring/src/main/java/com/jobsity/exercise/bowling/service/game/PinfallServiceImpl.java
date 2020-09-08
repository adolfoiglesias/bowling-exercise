/**
 * 
 */
package com.jobsity.exercise.bowling.service.game;

import com.jobsity.exercise.bowling.exception.BowlingGameException;
import com.jobsity.exercise.bowling.model.Frame;
import com.jobsity.exercise.bowling.model.Pinfall;
import com.jobsity.exercise.bowling.model.Player;
import org.springframework.stereotype.Service;

/**
 * @author Adolfo Miguel Iglesias
 *
 */
@Service
public class PinfallServiceImpl implements PinfallService {
	
	public PinfallServiceImpl() {
		super();
	}

	@Override
	public Pinfall recordPlay(Player player, String result) throws BowlingGameException {
		
		Frame  currentFrame = player.getCurrentFrame();
		
		Pinfall pinfall = currentFrame.getPinfall();
		
		pinfall.addResult(result);
		return pinfall;
		
	}
	
	@Override
	public void addCurrentFrameNumber(Player player, Pinfall pinfall) {
		if(pinfall.isClosed() && player.getCurrentFrameNumber() < player.getFrames().length) {
			player.setCurrentFrameNumber(player.getCurrentFrameNumber()+1);
		}
	}

}
