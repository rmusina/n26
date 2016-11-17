package com.n26.backend.metrics;


class EmptyMetricSet implements MetricSet {

    @Override
    public double getSum() {
        return 0;
    }

    @Override
    public double getAvg() {
        return 0;
    }

    @Override
    public double getMax() {
        return .0;
    }

    @Override
    public double getMin() {
        return .0;
    }

    @Override
    public int getCount() {
        return 0;
    }

    public MetricSet addValue(double value) {
        return this;
    }

    public MetricSet addMetricSet(MetricSet value) {
        return value;
    }

    public MetricSet reset() {
        return this;
    }
}
