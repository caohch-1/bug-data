# Bug Data
 Replication of train-ticket and collect logs, traces and metrics.

## Done
### MP-4637
Run `replcate.sh`.

For buggy run, comment L597 in `hadoop-mapreduce-client-app/src/test/java/org/apache/hadoop/mapreduce/v2/app/job/impl/TestTaskAttempt.java`. For normal run, activate L597.

The **error message function** is L982 of `handle()` in `hadoop-mapreduce-project/hadoop-mapreduce-client/hadoop-mapreduce-client-app/src/main/java/org/apache/hadoop/mapreduce/v2/app/job/impl/TaskAttemptImpl`.  

The **crime function** is 1) `killTask` in `hadoop-mapreduce-client-jobclient/src/main/java/org/apache/hadoop/mapred/ClientServiceDelegate` and its RPC `killTaskAttempt()` in `hadoop-mapreduce-client-app/src/main/java/org/apache/hadoop/mapreduce/v2/app/client/MRClientService`, 2) `heartbeat()` and its `handle()` in `hadoop-mapreduce-client-app/src/main/java/org/apache/hadoop/mapreduce/v2/app/local/LocalContainerAllocator`. 

The **root cause function** is `MRClientService.killTaskAttempt()` and `LocalContainerAllocator.handle()`

**Why:** When a `TaskAttempt` in state `UNASSIGNED`, if `LocalContainerAllocator.handle()` runs first, it transits to `Assigned`, then `MRClientService.killTaskAttempt()` runs, `TaskAttempt` receives `TA_DIAGNOSTICS_UPDATE` and `TA_KILL` and transits to `Assigned` and `KILLED`. However, if `MRClientService.killTaskAttempt()` runs first, `TaskAttempt` receives `TA_DIAGNOSTICS_UPDATE` and `TA_KILL` but it still in state `UNASSIGNED`, where it cannot handle `TA_DIAGNOSTICS_UPDATE`, which raises exception.
