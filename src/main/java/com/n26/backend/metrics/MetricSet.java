package com.n26.backend.metrics;


public interface MetricSet {
    double getSum();
    double getAvg();
    double getMax();
    double getMin();
    int getCount();

    MetricSet addValue(double value);
    MetricSet addMetricSet(MetricSet value);
    MetricSet reset();
}
