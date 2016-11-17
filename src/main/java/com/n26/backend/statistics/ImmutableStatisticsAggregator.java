package com.n26.backend.statistics;


class ImmutableStatisticsAggregator implements StatisticsAggregator {
    @Override
    public Statistics add(Statistics statistics, double value) {
        return new ImmutableStatistics(
            statistics.getCount() + 1,
            statistics.getSum() + value,
            Math.max(statistics.getMax(), value),
            Math.min(statistics.getMin(), value));
    }

    @Override
    public Statistics add(Statistics statistics1, Statistics statistics2) {
        return new ImmutableStatistics(
                statistics1.getCount() + statistics2.getCount(),
                statistics1.getSum() + statistics2.getSum(),
                Math.max(statistics1.getMax(), statistics2.getMax()),
                Math.min(statistics1.getMin(), statistics2.getMin()));
    }
}
