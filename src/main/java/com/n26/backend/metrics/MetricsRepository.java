package com.n26.backend.metrics;


public interface MetricsRepository {
    void registerMetric(double amount, long timestamp);
    MetricSet getStatisticsForInterval();
}
