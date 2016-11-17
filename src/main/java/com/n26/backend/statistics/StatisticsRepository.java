package com.n26.backend.statistics;


public interface StatisticsRepository {
    void registerStatistic(double amount, long timestamp);
    Statistics getStatisticsForInterval();
}
