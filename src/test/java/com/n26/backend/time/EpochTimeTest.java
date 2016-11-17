package com.n26.backend.time;

import org.junit.Assert;
import org.junit.Test;

public class EpochTimeTest {

    @Test
    public void whenValidMilisecondsTimeIsSpecifiedThenSecondsTimeIsReturnedSuccessfully() {
        Time time = new EpochTime(40000);
        long secondsTime = time.getRoundedSecondsEpoch();
        Assert.assertEquals(40, secondsTime);
    }
}
