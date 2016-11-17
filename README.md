## Building and testing

To build and run the application, just execute:

```mvn clean install exec:java```

To test that the endpoints are working, you can use the following ```curl``` commands:

```curl -H "Content-Type: application/json" -X POST -d '{ "amount": 120.0, "timestamp": 1479404871902 }' http://127.0.0.1:8080/transactions```

```curl -H "Content-Type: application/json" -H GET http://127.0.0.1:8080/statistics```


## Solution

### Algorithm

In order to achieve O(1) time&memory complexity for statistics fetching, I have chosen to use a circular array of 'buckets' where each bucket contains statistics aggregated for an entire second. When we get a /statistics request, we just have to add up the statistics in the 60 buckets that we have populated before. Because the number of buckest is constant and because we're not allocating extra data structures to aggregate those statistics (besides a single ImmutableStatistics object) the complexity for the aggregation is o(1).

### Handling older buckets

Whenever we update the contents of a bucket or read it for aggregation purposes, we check if the bucket timestamp falls within the current TimeTime interval (current time - interval duration), and always clear the content that we have found to be obsolete.

If a request is older, but still falls withing the current time interval, it will pe properly placed and aggregated in its bucket. 

### Threading considerations

The CircularBucket array class does most of the heavy-lifting for the statistics collection. Its implementation is based on an AtomicReferenceArray<Bucket>, and we use the accumulateAndGet method to ensure atomic writing to buckets. On the other hand, reading is not that strict (i.e. we do not block the whole array for the data aggregation part) and we can end up in situations where elements of the array are updated after we've read them for aggregation. While I could have blocked the entire array when reading the elements for aggregation purposes, I chose not to because of performance reasons and because it makes more sense to ensure fast writing (the more frequent operation) as opposed to getting a snapshot of the entire array at time t (instead we get snapshots of each bucket at time t, t+1...).

### Handling requests that are out of the time interval

I have decided to return 204 in case a request falls outside of the interval bounds. The main reason I chose this approach over returning 400 because in a real-world system, you would probably end up accepting the data and just sending it to a datawarehouse for further processing. 
