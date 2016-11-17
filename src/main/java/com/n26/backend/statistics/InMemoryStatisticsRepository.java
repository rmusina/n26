package com.n26.backend.statistics;

import com.n26.backend.time.EpochTime;
import com.n26.backend.time.TimeInterval;
import com.n26.backend.time.TimeIntervalProvider;

import javax.inject.Inject;

public class InMemoryStatisticsRepository implements StatisticsRepository {

    private final TimeIntervalProvider timeIntervalProvider;
    private final BucketArray bucketArray;

    @Inject
    public InMemoryStatisticsRepository(TimeIntervalProvider timeIntervalProvider) {
        this.timeIntervalProvider = timeIntervalProvider;
        int bucketCount = timeIntervalProvider.getIntervalDurationSeconds();
        this.bucketArray = new CircularBucketArray(bucketCount);
    }

    public void registerStatistics(double amount, long timestamp) {
        this.bucketArray.add(amount, new EpochTime(timestamp), timeIntervalProvider.get());
    }

    public Statistics getStatisticsForInterval() {
        Statistics aggregatedStatistics = new EmptyStatistics();
        StatisticsAggregator aggregator = new ImmutableStatisticsAggregator();
        TimeInterval currentInterval = timeIntervalProvider.get();

        for (int i = 0; i < bucketArray.size(); i++) {
            Bucket currentBucket = bucketArray.get(i);

            if (currentInterval.isInTimeInterval(currentBucket.getStartTime())) {
                aggregatedStatistics = aggregator.add(aggregatedStatistics, currentBucket.getBucketStatistics());
            }
        }

        return aggregatedStatistics;
    }
}
