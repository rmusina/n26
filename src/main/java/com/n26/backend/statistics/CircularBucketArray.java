package com.n26.backend.statistics;


import com.n26.backend.time.Time;
import com.n26.backend.time.TimeInterval;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReferenceArray;


class CircularBucketArray implements BucketArray {

    private int bucketCount;

    private AtomicReferenceArray<Bucket> buckets;

    CircularBucketArray(int bucketCount) {
        this.bucketCount = bucketCount;
        Bucket[] bucketArray = new EmptyBucket[bucketCount];
        Arrays.fill(bucketArray, new EmptyBucket());
        this.buckets = new AtomicReferenceArray<>(bucketArray);
    }

    public Bucket add(double value, Time time, TimeInterval timeInterval) {
        long roundedTime = time.getRoundedSecondsEpoch();
        int targetBucket = (int)roundedTime % bucketCount;
        Bucket newBucket = new TimeBasedBucket(time, new ImmutableStatistics(value));

        return this.buckets.accumulateAndGet(targetBucket, newBucket, new BucketMerger(timeInterval));
    }

    public Bucket get(int i) {
        return buckets.get(i);
    }

    public int size() {
        return this.bucketCount;
    }
}
