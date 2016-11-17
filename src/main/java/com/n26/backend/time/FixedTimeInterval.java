package com.n26.backend.time;

public class FixedTimeInterval implements TimeInterval {

    private final long timeInterval;

    private final Time intervalEndTime;

    public FixedTimeInterval(long timeInterval, Time intervalEndTime) {
        this.timeInterval = timeInterval;
        this.intervalEndTime = intervalEndTime;
    }

    public boolean isInTimeInterval(Time time) {
        long milisecondsEpoch = time.getMilisecondsEpoch();
        long endTimeMilisecondsEpoch = intervalEndTime.getMilisecondsEpoch();

        return milisecondsEpoch >= endTimeMilisecondsEpoch - timeInterval &&
               milisecondsEpoch <= endTimeMilisecondsEpoch;
    }
}
