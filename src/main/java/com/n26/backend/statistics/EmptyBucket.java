package com.n26.backend.statistics;

import com.n26.backend.time.EpochTime;
import com.n26.backend.time.Time;

class EmptyBucket implements Bucket {

    public Time getStartTime() {
        return new EpochTime(0);
    }

    public boolean isInTimeWindow(Time time) {
        return true;
    }

    public Statistics getBucketStatistics() {
        return new EmptyStatistics();
    }
}
