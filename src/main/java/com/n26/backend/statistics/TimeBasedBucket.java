package com.n26.backend.statistics;


public class TimeBasedBucket implements Bucket {

    private long time;

    private Statistics statistics;

    public TimeBasedBucket(long time, Statistics statistics) {
        this.time = time;
        this.statistics = statistics;
    }

    @Override
    public long getStartWindow() {
        return this.time;
    }

    @Override
    public boolean isInTimeWindow(long time) {
        return false;
    }

    @Override
    public Statistics getBucketStatistics() {
        return this.statistics;
    }
}
