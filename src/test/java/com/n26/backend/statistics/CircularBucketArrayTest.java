package com.n26.backend.statistics;


import com.n26.backend.time.EpochTime;
import com.n26.backend.time.FixedTimeInterval;
import org.junit.Assert;
import org.junit.Test;


public class CircularBucketArrayTest {
    @Test
    public void whenAddIsCalledForATimeInTheIntervalThenAdditionIsSuccessful() {
        BucketArray bucketArray = new CircularBucketArray(60);

        Bucket result = bucketArray.add(100, new EpochTime(10000), new FixedTimeInterval(60, new EpochTime(30000)));

        Assert.assertEquals(100, result.getBucketStatistics().getSum(), 0.01);
        Assert.assertEquals(result, bucketArray.get(10));
    }

    @Test
    public void whenAddIsCalledForATimeOutOfTheIntervalThenAdditionDoesNotModifyTheArray() {
        BucketArray bucketArray = new CircularBucketArray(60);

        Bucket result = bucketArray.add(100, new EpochTime(10000), new FixedTimeInterval(60, new EpochTime(80000)));

        Assert.assertEquals(EmptyBucket.class, result.getClass());
    }

    @Test
    public void whenAddIsCalledForATimeCorrespondingToAnAddedBucketThenTheValuesAreAggregated() {
        BucketArray bucketArray = new CircularBucketArray(60);

        bucketArray.add(100, new EpochTime(10000), new FixedTimeInterval(60, new EpochTime(40000)));
        Bucket result = bucketArray.add(20, new EpochTime(10234), new FixedTimeInterval(60, new EpochTime(40000)));

        Assert.assertEquals(TimeBasedBucket.class, result.getClass());
        Assert.assertEquals(120, result.getBucketStatistics().getSum(), 0.001);
        Assert.assertEquals(60, result.getBucketStatistics().getAvg(), 0.001);
        Assert.assertEquals(100, result.getBucketStatistics().getMax(), 0.001);
        Assert.assertEquals(20, result.getBucketStatistics().getMin(), 0.001);
    }
}
