package com.n26.backend.statistics;

public interface StatisticsAggregator {
    Statistics add(Statistics statistics, double value);
    Statistics add(Statistics stat1, Statistics stat2);
}
