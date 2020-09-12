package com.jobsity.exercise.bowling.service.output;

import com.jobsity.exercise.bowling.model.Pinfall;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("pinfallShowScoreService")
public class PinfallShowScoreServiceImpl  extends  AbstractPinfallShowScoreService {

    @Override
    public String showPinfallValue(Pinfall pinfall) {
        if (pinfall.isStrike()) {
            return "\t" + "X" + "\t";

        } else if (pinfall.isSpare()) {
            return showValue(pinfall.getValue1()) + showValue("/");

        } else {
            return showValue(pinfall.getValue1()) + showValue(pinfall.getValue1());
        }
    }
}
