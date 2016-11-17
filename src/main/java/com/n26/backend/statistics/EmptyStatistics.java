package com.n26.backend.statistics;


class EmptyStatistics implements Statistics {

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

    public Statistics addValue(double value) {
        return this;
    }

    public Statistics addStatistics(Statistics value) {
        return value;
    }

    public Statistics reset() {
        return this;
    }
}
