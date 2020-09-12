package com.jobsity.exercise.bowling.service.output;

import com.jobsity.exercise.bowling.model.LastPinfall;
import com.jobsity.exercise.bowling.model.Pinfall;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("lastPinfallShowScoreService")
public class LastPinfallShowScoreServiceImpl extends AbstractPinfallShowScoreService {

    @Override
    public String showPinfallValue(Pinfall pinfall) {

        LastPinfall lastPinfall = (LastPinfall) pinfall;
        String value1Temp = "";

        if(isValueX(lastPinfall.getValue1())) {
            value1Temp = "X";
        }else {
            value1Temp = lastPinfall.getValue1();
        }

        String value2Temp = "";
        if(isValueX(lastPinfall.getValue2())) {
            value2Temp = "X";

        }else if(lastPinfall.getValue1Number() + lastPinfall.getValue2Number() == 10 && lastPinfall.getValue2Number() != 0) {
            value2Temp = "/";
        } else {
            value2Temp = lastPinfall.getValue2();
        }


        String value3Temp = "";
        if(isValueX(lastPinfall.getValue3())) {
            value3Temp = "X";

        }else if(lastPinfall.getValue3Number() != 0 && ((lastPinfall.getValue2Number() + lastPinfall.getValue3Number() == 10 )
                || (lastPinfall.getValue1Number() + lastPinfall.getValue2Number() + lastPinfall.getValue3Number() == 10 ) )) {

            value3Temp = "/";
        } else {
            value3Temp = lastPinfall.getValue3();
        }

        return showValue(value1Temp) + showValue(value2Temp) + showValue(value3Temp);
    }
    protected boolean isValueX(String value) {
        return value.equals("10");
    }

}
