package com.n26.backend.metrics;


class ImmutableMetricSet implements MetricSet {
    private final int count;
    private final double sum;
    private final double max;
    private final double min;

    public ImmutableMetricSet(double sum) {
        this.count = 1;
        this.sum = sum;
        this.max = sum;
        this.min = sum;
    }

    public ImmutableMetricSet(int count, double sum, double max, double min) {
        this.count = count;
        this.sum = sum;
        this.max = max;
        this.min = min;
    }

    public int getCount() {
        return count;
    }

    public double getAvg() {
        return count == 0 ? 0 : sum/count;
    }

    public double getSum() {
        return sum;
    }

    public double getMax() {
        return max;
    }

    public double getMin() {
        return min;
    }

    @Override
    public MetricSet addValue(double value) {
        return null;
    }

    @Override
    public MetricSet addMetricSet(MetricSet value) {
        return null;
    }

    @Override
    public MetricSet reset() {
        return new EmptyMetricSet();
    }
}
