package com.n26.backend.statistics;


interface Bucket {
    long getStartWindow();
    boolean isInTimeWindow(long time);
    Statistics getBucketStatistics();
}
