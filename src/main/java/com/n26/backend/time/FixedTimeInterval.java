package com.n26.backend.time;

public class FixedTimeInterval implements TimeInterval {

    private final long intervalDurationSeconds;

    private final Time intervalEndTime;

    public FixedTimeInterval(long intervalDurationSeconds, Time intervalEndTime) {
        this.intervalDurationSeconds = intervalDurationSeconds;
        this.intervalEndTime = intervalEndTime;
    }

    public boolean isInTimeInterval(Time time) {
        long milisecondsEpoch = time.getRoundedSecondsEpoch();
        long endTimeMilisecondsEpoch = intervalEndTime.getRoundedSecondsEpoch();

        return milisecondsEpoch >= endTimeMilisecondsEpoch - intervalDurationSeconds &&
               milisecondsEpoch <= endTimeMilisecondsEpoch;
    }
}
