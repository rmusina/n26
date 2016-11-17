package com.n26.backend.statistics;


class EmptyStatistics implements Statistics {

    public double getSum() {
        return .0;
    }

    public double getAvg() {
        return .0;
    }

    public double getMax() {
        return Double.NEGATIVE_INFINITY;
    }

    public double getMin() {
        return Double.POSITIVE_INFINITY;
    }

    public long getCount() {
        return 0L;
    }
}
