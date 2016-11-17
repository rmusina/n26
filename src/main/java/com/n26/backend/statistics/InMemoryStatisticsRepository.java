package com.n26.backend.statistics;

import com.n26.backend.time.EpochTime;
import com.n26.backend.time.FixedTimeInterval;
import com.n26.backend.time.TimeInterval;

import java.time.Instant;

public class InMemoryStatisticsRepository implements StatisticsRepository {

    private int timeInterval;

    private BucketArray bucketArray;

    public InMemoryStatisticsRepository() {
        this.timeInterval = 60;
        this.bucketArray = new CircularBucketArray(60);
    }

    public void registerStatistics(double amount, long timestamp) {
        this.bucketArray.add(amount, new EpochTime(timestamp), getCurrentTimeInterval());
    }

    public Statistics getStatisticsForInterval() {
        Statistics aggregatedStatistics = new EmptyStatistics();
        StatisticsAggregator aggregator = new ImmutableStatisticsAggregator();
        TimeInterval currentInterval = getCurrentTimeInterval();

        for (int i = 0; i < bucketArray.size(); i++) {
            Bucket currentBucket = bucketArray.get(i);

            if (currentInterval.isInTimeInterval(currentBucket.getStartTime())) {
                aggregatedStatistics = aggregator.add(aggregatedStatistics, currentBucket.getBucketStatistics());
            }
        }

        return aggregatedStatistics;
    }

    private TimeInterval getCurrentTimeInterval() {
        return new FixedTimeInterval(timeInterval, new EpochTime(Instant.now().toEpochMilli()));
    }
}
