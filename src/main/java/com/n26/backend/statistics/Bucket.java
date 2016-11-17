package com.n26.backend.statistics;


import com.n26.backend.time.Time;

interface Bucket {
    Time getStartTime();
    Statistics getBucketStatistics();
}
