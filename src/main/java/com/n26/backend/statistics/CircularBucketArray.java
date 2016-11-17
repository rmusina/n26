package com.n26.backend.statistics;


import com.n26.backend.time.EpochTime;
import com.n26.backend.time.Time;
import com.n26.backend.time.TimeInterval;

import java.util.concurrent.atomic.AtomicReferenceArray;


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
