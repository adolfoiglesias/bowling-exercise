/**
 * 
 */
package com.jobsity.bowling.service.game;

import com.jobsity.bowling.exception.BowlingGameException;
import com.jobsity.bowling.model.Frame;
import com.jobsity.bowling.model.Pinfall;
import com.jobsity.bowling.model.Player;

/**
 * @author Adolfo Miguel Iglesias
 *
 */
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
