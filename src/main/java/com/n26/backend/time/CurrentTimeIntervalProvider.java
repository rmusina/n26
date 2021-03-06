package com.n26.backend.time;

import java.time.Instant;

public class CurrentTimeIntervalProvider implements TimeIntervalProvider {

    private final long intervalDurationSeconds;

    public CurrentTimeIntervalProvider(long intervalDurationSeconds) {
        this.intervalDurationSeconds = intervalDurationSeconds;
    }

    public TimeInterval get() {
        return new FixedTimeInterval(intervalDurationSeconds, new EpochTime(Instant.now().toEpochMilli()));
    }

    public int getIntervalDurationSeconds() {
        return (int)intervalDurationSeconds;
    }
}
