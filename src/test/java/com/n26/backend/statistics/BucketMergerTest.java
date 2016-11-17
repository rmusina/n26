package com.n26.backend.statistics;

import com.n26.backend.time.EpochTime;
import com.n26.backend.time.FixedTimeInterval;
import com.n26.backend.time.Time;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.when;

public class BucketMergerTest {

    private final double eps = 0.001;

    @Test
    public void whenAddingAValidBucketOverAnEmptyBucketThenTheNewBucketReplacesTheEmptyOne() {
        FixedTimeInterval timeInterval = Mockito.mock(FixedTimeInterval.class);
        when(timeInterval.isInTimeInterval(isA(Time.class))).thenReturn(true);
        BucketMerger merger = new BucketMerger(timeInterval);

        Bucket newBucket = new TimeBasedBucket(new EpochTime(200), new EmptyStatistics());
        Bucket result = merger.apply(new EmptyBucket(), newBucket);

        Assert.assertSame(result, newBucket);
    }

    @Test
    public void whenAddingAnInvalidBucketOverAnEmptyBucketThenTheBucketRemainsEmpty() {
        FixedTimeInterval timeInterval = Mockito.mock(FixedTimeInterval.class);
        when(timeInterval.isInTimeInterval(isA(Time.class))).thenReturn(false);
        BucketMerger merger = new BucketMerger(timeInterval);

        Bucket newBucket = new TimeBasedBucket(new EpochTime(200), new EmptyStatistics());
        Bucket emptyBucket = new EmptyBucket();
        Bucket result = merger.apply(emptyBucket, newBucket);

        Assert.assertEquals(EmptyBucket.class, result.getClass() );
    }

    @Test
    public void whenAddingAnInvalidBucketOverAValidBucketThenTheValidBucketIsNotChanged() {
        Time validTime = new EpochTime(1);
        Time invalidTime = new EpochTime(2);
        FixedTimeInterval timeInterval = Mockito.mock(FixedTimeInterval.class);
        when(timeInterval.isInTimeInterval(validTime)).thenReturn(true);
        when(timeInterval.isInTimeInterval(invalidTime)).thenReturn(false);
        BucketMerger merger = new BucketMerger(timeInterval);

        Bucket newBucket = new TimeBasedBucket(invalidTime, new EmptyStatistics());
        Bucket currentBucket = new TimeBasedBucket(validTime, new EmptyStatistics());
        Bucket result = merger.apply(currentBucket, newBucket);

        Assert.assertSame(currentBucket, result);
    }

    @Test
    public void whenAddingAValidBucketOverAnotherValidBucketThenTheBucketsAreAggregated() {
        FixedTimeInterval timeInterval = Mockito.mock(FixedTimeInterval.class);
        when(timeInterval.isInTimeInterval(isA(Time.class))).thenReturn(true);
        BucketMerger merger = new BucketMerger(timeInterval);

        Bucket newBucket = new TimeBasedBucket(new EpochTime(1), new ImmutableStatistics(10));
        Bucket currentBucket = new TimeBasedBucket(new EpochTime(1), new ImmutableStatistics(20));
        Bucket result = merger.apply(currentBucket, newBucket);

        Statistics resultStatistics = result.getBucketStatistics();
        Assert.assertEquals(30, resultStatistics.getSum(), eps);
        Assert.assertEquals(15, resultStatistics.getAvg(), eps);
        Assert.assertEquals(20, resultStatistics.getMax(), eps);
        Assert.assertEquals(10, resultStatistics.getMin(), eps);
    }
}
