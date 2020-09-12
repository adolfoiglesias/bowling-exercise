package com.jobsity.exercise.bowling.service.output;

import com.jobsity.exercise.bowling.model.Pinfall;

abstract public class AbstractPinfallShowScoreService implements  PinfallShowScoreService {

    protected String showValue(String value) {
        return value + "\t";
    }
}
