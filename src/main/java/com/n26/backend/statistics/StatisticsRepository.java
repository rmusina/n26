package com.n26.backend.statistics;


public interface StatisticsRepository {
    void registerStatistics(double amount, long timestamp);
    Statistics getStatisticsForInterval();
}
