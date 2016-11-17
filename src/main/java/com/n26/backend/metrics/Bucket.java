package com.n26.backend.metrics;


interface Bucket {
    long getStartWindow();
    boolean isInTimeWindow(long time);
    MetricSet getBucketMetrics();
}
