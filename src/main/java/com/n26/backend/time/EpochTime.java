package com.n26.backend.time;

import java.util.Calendar;

public class EpochTime implements Time {
    private long millisecondsEpoch;

    private long secondsEpoch;

    public EpochTime(long millisecondsEpoch) {
        this.millisecondsEpoch = millisecondsEpoch;
    }

    public long getMilisecondsEpoch() {
        return millisecondsEpoch;
    }

    public long getRoundedSecondsEpoch() {
        if (secondsEpoch > 0) {
            return secondsEpoch;
        }

        secondsEpoch = millisecondsEpoch/1000L;

        return secondsEpoch;
    }
}
