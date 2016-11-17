package com.n26.backend.statistics;


import com.n26.backend.time.Time;
import com.n26.backend.time.TimeInterval;

interface BucketArray {
    Bucket add(double value, Time time, TimeInterval timeInterval);
    Bucket get(int i);
    int size();
}
