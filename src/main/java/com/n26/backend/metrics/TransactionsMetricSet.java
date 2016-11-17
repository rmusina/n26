package com.n26.backend.metrics;


public class TransactionsMetricSet implements MetricSet {
    private int count = 0;
    private double sum = .0;
    private double max = Double.NEGATIVE_INFINITY;
    private double min = Double.POSITIVE_INFINITY;

    @Override
    public double getSum() {
        return this.sum;
    }

    @Override
    public double getAvg() {
        return 0;
    }

    @Override
    public double getMax() {
        return this.max;
    }

    @Override
    public double getMin() {
        return this.min;
    }

    @Override
    public int getCount() {
        return this.count;
    }

    public MetricSet addValue(double value) {
        count++;
        sum += value;
        max = Math.max(max, value);
        min = Math.min(min, value);

        return this;
    }

    public MetricSet addMetricSet(MetricSet value) {
        //TODO: add code here
        return null;
    }

    public MetricSet reset() {
        count = 0;
        sum = 0;
        max = Double.NEGATIVE_INFINITY;
        min = Double.POSITIVE_INFINITY;

        return this;
    }
}
