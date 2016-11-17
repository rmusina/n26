package com.n26.backend.statistics;


interface BucketArray {
    Bucket addToBucket(double value, long time);
    Bucket get(int i);
    int size();
}
