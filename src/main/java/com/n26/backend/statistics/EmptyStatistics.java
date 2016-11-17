package com.n26.backend.statistics;


class EmptyStatistics implements Statistics {

    public double getSum() {
        return .0;
    }

    public double getAvg() {
        return .0;
    }

    public double getMax() {
        return .0;
    }

    public double getMin() {
        return .0;
    }

    public int getCount() {
        return 0;
    }
}
