package com.n26.backend.statistics;

import java.time.Instant;

public class InMemoryStatisticsRepository implements StatisticsRepository {

    private int timeInterval;

    private BucketArray bucketArray;

    public InMemoryStatisticsRepository() {
        this.timeInterval = 60;
        this.bucketArray = new CircularBucketArray(60);
    }

    public void registerStatistic(double amount, long timestamp) {
        this.bucketArray.addToBucket(amount, timestamp);
    }

    public Statistics getStatisticsForInterval() {
        long currentTime = Instant.now().toEpochMilli();
        Statistics totalSnapshot = new TransactionsStatistics();

        for (int i = 0; i < bucketArray.size(); i++) {
            Bucket currentBucket = bucketArray.get(i);
            if (currentBucket.isInTimeWindow(currentTime)) {
                totalSnapshot.addStatistics(currentBucket.getBucketStatistics());
            }
        }

        return totalSnapshot;
    }
}
