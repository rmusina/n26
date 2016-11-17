package com.n26.backend.time;

import org.junit.Assert;
import org.junit.Test;

public class FixedTimeIntervalTest {
    
    private static final int INTERVAL_SIZE = 60;

    @Test
    public void whenTimeIsAtTheEdgeOfTimeIntervalButInsideItThenIsInIntervalReturnsTrue() {
        TimeInterval timeInterval = new FixedTimeInterval(INTERVAL_SIZE, new EpochTime(60000));
        boolean result = timeInterval.isInTimeInterval(new EpochTime(59999));
        Assert.assertEquals(true, result);
    }

    @Test
    public void whenTimeIsInTheMiddleOfTheIntervalThenIsInIntervalReturnsTrue() {
        TimeInterval timeInterval = new FixedTimeInterval(INTERVAL_SIZE, new EpochTime(60000));
        boolean result = timeInterval.isInTimeInterval(new EpochTime(24934));
        Assert.assertEquals(true, result);
    }

    @Test
    public void whenTimeIsOutsideOfTheIntervalThenIsInIntervalReturnsFalse() {
        TimeInterval timeInterval = new FixedTimeInterval(INTERVAL_SIZE, new EpochTime(60000));
        boolean result = timeInterval.isInTimeInterval(new EpochTime(100000));
        Assert.assertEquals(false, result);
    }
}
