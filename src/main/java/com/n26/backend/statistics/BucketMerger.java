package com.n26.backend.statistics;

import com.n26.backend.time.TimeInterval;

import java.util.function.BinaryOperator;

class BucketMerger implements BinaryOperator<Bucket> {

    private final TimeInterval timeInterval;

    BucketMerger(TimeInterval timeInterval) {
        this.timeInterval = timeInterval;
    }

    public Bucket apply(Bucket bucket1, Bucket bucket2) {
        if (EmptyBucket.class.isInstance(bucket1) ||
            !timeInterval.isInTimeInterval(bucket1.getStartTime())) {
            return timeInterval.isInTimeInterval(bucket2.getStartTime()) ? bucket2 : new EmptyBucket();
        }

        if (!timeInterval.isInTimeInterval(bucket2.getStartTime())) {
            return bucket1;
        }

        StatisticsAggregator aggregator = new ImmutableStatisticsAggregator();
        Statistics aggregatedStatistics = aggregator.add(bucket1.getBucketStatistics(), bucket2.getBucketStatistics());

        return new TimeBasedBucket(bucket1.getStartTime(), aggregatedStatistics);
    }
}
