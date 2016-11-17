package com.n26.backend.statistics;

import com.n26.backend.time.EpochTime;
import com.n26.backend.time.FixedTimeInterval;
import com.n26.backend.time.Time;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.when;

public class BucketMergerTests {
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
    }

    @Test
    public void whenAddingAValidBucketOverAnotherValidBucketThenTheBucketsAreAggregated() {

    }
}
