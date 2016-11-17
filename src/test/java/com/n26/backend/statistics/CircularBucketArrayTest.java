package com.n26.backend.statistics;


import com.n26.backend.time.EpochTime;
import com.n26.backend.time.FixedTimeInterval;
import org.junit.Assert;
import org.junit.Test;


public class CircularBucketArrayTest {

    private final double EPS = 0.001;

    private final int INTERVAL_SIZE = 60;

    @Test
    public void whenAddIsCalledForATimeInTheIntervalThenAdditionIsSuccessful() {
        BucketArray bucketArray = new CircularBucketArray(INTERVAL_SIZE);

        Bucket result = bucketArray.add(100, new EpochTime(10000), new FixedTimeInterval(INTERVAL_SIZE, new EpochTime(30000)));

        Assert.assertEquals(100, result.getBucketStatistics().getSum(), EPS);
        Assert.assertEquals(result, bucketArray.get(10));
    }

    @Test
    public void whenAddIsCalledForATimeOutOfTheIntervalThenAdditionDoesNotModifyTheArray() {
        BucketArray bucketArray = new CircularBucketArray(INTERVAL_SIZE);

        Bucket result = bucketArray.add(100, new EpochTime(10000), new FixedTimeInterval(INTERVAL_SIZE, new EpochTime(80000)));

        Assert.assertEquals(EmptyBucket.class, result.getClass());
    }

    @Test
    public void whenAddIsCalledForATimeCorrespondingToAnAddedBucketThenTheValuesAreAggregated() {
        BucketArray bucketArray = new CircularBucketArray(INTERVAL_SIZE);

        bucketArray.add(100, new EpochTime(10000), new FixedTimeInterval(INTERVAL_SIZE, new EpochTime(40000)));
        Bucket result = bucketArray.add(20, new EpochTime(10234), new FixedTimeInterval(INTERVAL_SIZE, new EpochTime(40000)));

        Assert.assertEquals(TimeBasedBucket.class, result.getClass());
        Assert.assertEquals(120, result.getBucketStatistics().getSum(), EPS);
        Assert.assertEquals(INTERVAL_SIZE, result.getBucketStatistics().getAvg(), EPS);
        Assert.assertEquals(100, result.getBucketStatistics().getMax(), EPS);
        Assert.assertEquals(20, result.getBucketStatistics().getMin(), EPS);
        Assert.assertEquals(2, result.getBucketStatistics().getCount());
    }

    @Test
    public void whenAddIsCalledForATimeCorrespondingToAnAddedBucketThatExpiredThenTheNewBucketReplacesIt() {
        BucketArray bucketArray = new CircularBucketArray(INTERVAL_SIZE);

        bucketArray.add(100, new EpochTime(10000), new FixedTimeInterval(INTERVAL_SIZE, new EpochTime(40000)));
        Bucket result1 = bucketArray.get(10);

        Assert.assertEquals(TimeBasedBucket.class, result1.getClass());
        Assert.assertEquals(100, result1.getBucketStatistics().getSum(), EPS);
        Assert.assertEquals(100, result1.getBucketStatistics().getAvg(), EPS);
        Assert.assertEquals(100, result1.getBucketStatistics().getMax(), EPS);
        Assert.assertEquals(100, result1.getBucketStatistics().getMin(), EPS);
        Assert.assertEquals(1, result1.getBucketStatistics().getCount());

        bucketArray.add(20, new EpochTime(70234), new FixedTimeInterval(INTERVAL_SIZE, new EpochTime(120000)));
        Bucket result2 = bucketArray.get(10);

        Assert.assertEquals(TimeBasedBucket.class, result2.getClass());
        Assert.assertEquals(20, result2.getBucketStatistics().getSum(), EPS);
        Assert.assertEquals(20, result2.getBucketStatistics().getAvg(), EPS);
        Assert.assertEquals(20, result2.getBucketStatistics().getMax(), EPS);
        Assert.assertEquals(20, result2.getBucketStatistics().getMin(), EPS);
        Assert.assertEquals(1, result2.getBucketStatistics().getCount());
    }
}
