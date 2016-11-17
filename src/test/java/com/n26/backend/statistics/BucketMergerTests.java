package com.n26.backend.statistics;

import com.n26.backend.time.EpochTime;
import com.n26.backend.time.FixedTimeInterval;
import org.junit.Test;

public class BucketMergerTests {
    @Test
    public void whenAddingAValidBucketOverAnEmptyBucketThenTheNewBucketReplacesTheEmptyOne() {
        BucketMerger merger = new BucketMerger(new FixedTimeInterval(60, new EpochTime(60000)));
    }

    @Test
    public void whenAddingAnInvalidBucketOverAnEmptyBucketThenTheBucketRemainsEmpty() {
    }

    @Test
    public void whenAddingAnInvalidBucketOverAValidBucketThenTheValidBucketIsNotChanged() {
    }

    public void whenAddingAValidBucketOverAnotherValidBucketThenTheBucketsAreAggregated() {

    }
}
