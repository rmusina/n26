package com.n26.backend.statistics;


import com.n26.backend.time.Time;

class TimeBasedBucket implements Bucket {

    private final Time startTime;

    private final Statistics statistics;

    TimeBasedBucket(Time startTime, Statistics statistics) {
        this.startTime = startTime;
        this.statistics = statistics;
    }

    public Time getStartTime() {
        return startTime;
    }

    public boolean isInTimeWindow(Time time) {
        return time.getRoundedSecondsEpoch() == startTime.getRoundedSecondsEpoch();
    }

    public Statistics getBucketStatistics() {
        return statistics;
    }
}
