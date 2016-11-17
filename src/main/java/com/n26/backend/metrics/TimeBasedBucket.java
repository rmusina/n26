package com.n26.backend.metrics;


public class TimeBasedBucket implements Bucket {

    private long time;

    private MetricSet metricSet;

    public TimeBasedBucket(long time, MetricSet metricSet) {
        this.time = time;
        this.metricSet = metricSet;
    }

    @Override
    public long getStartWindow() {
        return 0;
    }

    @Override
    public boolean isInTimeWindow(long time) {
        return false;
    }

    @Override
    public MetricSet getBucketMetrics() {
        return null;
    }
}
