package com.n26.backend.statistics;

import com.n26.backend.time.Time;

class EmptyBucket implements Bucket {

    public Time getStartTime() {
        return null;
    }

    public boolean isInTimeWindow(Time time) {
        return true;
    }

    public Statistics getBucketStatistics() {
        return null;
    }
}
