package com.n26.backend.metrics;

import java.time.Instant;

public class InMemoryMetricsRepository implements MetricsRepository {

    private int timeInterval;

    private BucketArray bucketArray;

    public InMemoryMetricsRepository() {
        this.timeInterval = 60;
        this.bucketArray = new CircularBucketArray(60);
    }

    public void registerMetric(double amount, long timestamp) {
        this.bucketArray.addToBucket(amount, timestamp);
    }

    public MetricSet getStatisticsForInterval() {
        long currentTime = Instant.now().toEpochMilli();
        MetricSet totalSnapshot = new TransactionsMetricSet();

        for (int i = 0; i < bucketArray.size(); i++) {
            Bucket currentBucket = bucketArray.get(i);
            if (currentBucket.isInTimeWindow(currentTime)) {
                totalSnapshot.addMetricSet(currentBucket.getBucketMetrics());
            }
        }

        return totalSnapshot;
    }
}
