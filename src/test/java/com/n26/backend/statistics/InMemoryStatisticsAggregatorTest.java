package com.n26.backend.statistics;

import com.n26.backend.time.EpochTime;
import com.n26.backend.time.FixedTimeInterval;
import com.n26.backend.time.TimeIntervalProvider;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class InMemoryStatisticsAggregatorTest {

    private final double EPS = 0.001;

    private final int INTERVAL_SIZE = 60;

    @Test
    public void whenAllBucketsAreInTheCurrentTimeIntervalThenTheyAreAllAggregated() {
        TimeIntervalProvider timeIntervalProvider = Mockito.mock(TimeIntervalProvider.class);
        when(timeIntervalProvider.getIntervalDurationSeconds()).thenReturn(INTERVAL_SIZE);
        when(timeIntervalProvider.get()).thenReturn(new FixedTimeInterval(INTERVAL_SIZE, new EpochTime(91000)));
        StatisticsRepository repository = new InMemoryStatisticsRepository(timeIntervalProvider);

        repository.registerStatistics(100, 60000);
        repository.registerStatistics(50, 70000);
        repository.registerStatistics(10, 80000);
        repository.registerStatistics(30, 90000);

        Statistics statistics = repository.getStatisticsForInterval();

        Assert.assertEquals(190, statistics.getSum(), EPS);
        Assert.assertEquals(47.5, statistics.getAvg(), EPS);
        Assert.assertEquals(100, statistics.getMax(), EPS);
        Assert.assertEquals(10, statistics.getMin(), EPS);
        Assert.assertEquals(4, statistics.getCount());
    }

    @Test
    public void whenSomeBucketsAreOutsideOfTheCurrentTimeIntervalThenTheyAreIgnored() {
        TimeIntervalProvider timeIntervalProvider = Mockito.mock(TimeIntervalProvider.class);
        when(timeIntervalProvider.getIntervalDurationSeconds()).thenReturn(INTERVAL_SIZE);
        when(timeIntervalProvider.get()).thenReturn(new FixedTimeInterval(INTERVAL_SIZE, new EpochTime(140000)));
        StatisticsRepository repository = new InMemoryStatisticsRepository(timeIntervalProvider);

        repository.registerStatistics(100, 60000);
        repository.registerStatistics(50, 70000);
        repository.registerStatistics(10, 80000);
        repository.registerStatistics(30, 90000);

        Statistics statistics = repository.getStatisticsForInterval();

        Assert.assertEquals(40, statistics.getSum(), EPS);
        Assert.assertEquals(20, statistics.getAvg(), EPS);
        Assert.assertEquals(30, statistics.getMax(), EPS);
        Assert.assertEquals(10, statistics.getMin(), EPS);
        Assert.assertEquals(2, statistics.getCount());
    }
}
