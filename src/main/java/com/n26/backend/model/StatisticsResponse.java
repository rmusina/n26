package com.n26.backend.model;


import com.n26.backend.statistics.Statistics;

public class StatisticsResponse {
    public double sum;
    public double avg;
    public double max;
    public double min;
    public int count;

    public StatisticsResponse(Statistics statistics) {
        this.sum = statistics.getSum();
        this.avg = statistics.getAvg();
        this.max = statistics.getMax();
        this.min = statistics.getMin();
        this.count = statistics.getCount();
    }
}
