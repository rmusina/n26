package com.n26.backend.statistics;

import com.n26.backend.time.EpochTime;
import com.n26.backend.time.FixedTimeInterval;
import com.n26.backend.time.TimeIntervalProvider;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class InMemoryStatisticsAggregatorTest {

    @Test
    public void whenAllBucketsAreInTheCurrentTimeIntervalThenTheyAreAllAggregated() {
        TimeIntervalProvider timeIntervalProvider = Mockito.mock(TimeIntervalProvider.class);
        when(timeIntervalProvider.get()).thenReturn(new FixedTimeInterval(60, new EpochTime(91000)));
        StatisticsRepository repository = new InMemoryStatisticsRepository(timeIntervalProvider);

        repository.registerStatistics(100, 60000);
        repository.registerStatistics(50, 70000);
        repository.registerStatistics(10, 80000);
        repository.registerStatistics(30, 90000);

        Statistics statistics = repository.getStatisticsForInterval();

        Assert.assertEquals(190, statistics.getSum(), 0.001);
        Assert.assertEquals(47.5, statistics.getAvg(), 0.001);
        Assert.assertEquals(100, statistics.getMax(), 0.001);
        Assert.assertEquals(10, statistics.getMin(), 0.001);
        Assert.assertEquals(4, statistics.getCount());
    }

    @Test
    public void whenSomeBucketsAreOutsideOfTheCurrentTimeIntervalThenTheyAreIgnored() {
        TimeIntervalProvider timeIntervalProvider = Mockito.mock(TimeIntervalProvider.class);
        when(timeIntervalProvider.get()).thenReturn(new FixedTimeInterval(60, new EpochTime(140000)));
        StatisticsRepository repository = new InMemoryStatisticsRepository(timeIntervalProvider);

        repository.registerStatistics(100, 60000);
        repository.registerStatistics(50, 70000);
        repository.registerStatistics(10, 80000);
        repository.registerStatistics(30, 90000);

        Statistics statistics = repository.getStatisticsForInterval();

        Assert.assertEquals(40, statistics.getSum(), 0.001);
        Assert.assertEquals(20, statistics.getAvg(), 0.001);
        Assert.assertEquals(30, statistics.getMax(), 0.001);
        Assert.assertEquals(10, statistics.getMin(), 0.001);
        Assert.assertEquals(2, statistics.getCount());
    }
}
