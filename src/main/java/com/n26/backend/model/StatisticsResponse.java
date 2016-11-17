package com.n26.backend.model;


import com.n26.backend.metrics.MetricSet;

public class StatisticsResponse {
    public double sum;
    public double avg;
    public double max;
    public double min;
    public int count;

    public StatisticsResponse(MetricSet metricSet) {
        this.sum = metricSet.getSum();
        this.avg = metricSet.getAvg();
        this.max = metricSet.getMax();
        this.min = metricSet.getMin();
        this.count = metricSet.getCount();
    }
}
