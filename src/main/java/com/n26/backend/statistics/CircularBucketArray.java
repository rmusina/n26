package com.n26.backend.statistics;


import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.function.BinaryOperator;


class BucketMerger implements BinaryOperator<Bucket> {

    public Bucket apply(Bucket bucket, Bucket bucket2) {
        if (bucket2.getStartWindow() != bucket.getStartWindow() && bucket2.getStartWindow() > bucket.getStartWindow()) {
            return bucket2;
        }

        Statistics existingStatistics = bucket.getBucketStatistics();
        Statistics cummulativeStatistics = existingStatistics.addStatistics(bucket2.getBucketStatistics());

        return new TimeBasedBucket(bucket.getStartWindow(), cummulativeStatistics);
    }
}


public class CircularBucketArray implements BucketArray {

    private int bucketCount;

    private AtomicReferenceArray<Bucket> buckets;

    public CircularBucketArray(int bucketCount) {
        this.bucketCount = bucketCount;
        this.buckets = new AtomicReferenceArray<>(new Bucket[bucketCount]);
    }

    public Bucket addToBucket(double value, long time) {
        long roundedTime = roundTime(time);
        int targetBucket = (int)roundedTime % bucketCount;
        Bucket newBucket = new TimeBasedBucket(roundedTime, new ImmutableStatistics(value));

        return this.buckets.accumulateAndGet(targetBucket, newBucket, new BucketMerger());
    }

    private long roundTime(long time) {
        return time;
    }

    public Bucket get(int i) {
        return this.get(i);
    }

    public int size() {
        return this.bucketCount;
    }
}
