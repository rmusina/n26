package com.n26.backend.statistics;


import com.n26.backend.time.EpochTime;
import com.n26.backend.time.Time;
import com.n26.backend.time.TimeInterval;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.function.BinaryOperator;


class BucketMerger implements BinaryOperator<Bucket> {

    private final TimeInterval timeInterval;

    BucketMerger(TimeInterval timeInterval) {
        this.timeInterval = timeInterval;
    }

    public Bucket apply(Bucket bucket1, Bucket bucket2) {
        long currentTime = Instant.now().toEpochMilli();
        long bucket1Time = bucket1.getStartTime().getMilisecondsEpoch();

        if (!bucket1.isInTimeWindow(bucket2.getStartTime())) {
            return bucket2;
        }

        StatisticsAggregator aggregator = new ImmutableStatisticsAggregator();
        Statistics aggregatedStatistics = aggregator.add(bucket1.getBucketStatistics(), bucket2.getBucketStatistics());

        return new TimeBasedBucket(bucket1.getStartTime(), aggregatedStatistics);
    }
}


class CircularBucketArray implements BucketArray {

    private int bucketCount;

    private AtomicReferenceArray<Bucket> buckets;

    CircularBucketArray(int bucketCount) {
        this.bucketCount = bucketCount;
        this.buckets = new AtomicReferenceArray<>(new Bucket[bucketCount]);
    }

    public Bucket add(double value, Time time, TimeInterval timeInterval) {
        long roundedTime = time.getRoundedSecondsEpoch();
        int targetBucket = (int)roundedTime % bucketCount;
        Bucket newBucket = new TimeBasedBucket(new EpochTime(roundedTime), new ImmutableStatistics(value));

        return this.buckets.accumulateAndGet(targetBucket, newBucket, new BucketMerger(timeInterval));
    }

    public Bucket get(int i) {
        return buckets.get(i);
    }

    public int size() {
        return this.bucketCount;
    }
}
