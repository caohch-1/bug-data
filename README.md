# Bug Data
 Replication of train-ticket and collect logs, traces and metrics.

## Done
### MP-4637
run `replcate.sh`, changed dependency client-app to 2.0.0 and corresponding code (i.e., L580) in `MP-4637/hadoop-mapreduce-client-app/src/test/java/org/apache/hadoop/mapreduce/v2/app/job/impl/TestTaskAttempt.java` will trigger bug.
