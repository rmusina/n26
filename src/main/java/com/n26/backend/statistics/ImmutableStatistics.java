package com.n26.backend.statistics;


class ImmutableStatistics implements Statistics {
    private final long count;
    private final double sum;
    private final double max;
    private final double min;

    ImmutableStatistics(double sum) {
        this.count = 1;
        this.sum = sum;
        this.max = sum;
        this.min = sum;
    }

    ImmutableStatistics(long count, double sum, double max, double min) {
        this.count = count;
        this.sum = sum;
        this.max = max;
        this.min = min;
    }

    public long getCount() {
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
}
