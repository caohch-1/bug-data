# Bug Data
 Replication of train-ticket and collect logs, traces and metrics.

## Done
### MP-4637
Run `replcate.sh`.

The **error message function** is L982 of `handle()` in `hadoop-mapreduce-project/hadoop-mapreduce-client/hadoop-mapreduce-client-app/src/main/java/org/apache/hadoop/mapreduce/v2/app/job/impl/TaskAttemptImpl`.  

The **crime function** is 1) `killTask` in `hadoop-mapreduce-client-jobclient/src/main/java/org/apache/hadoop/mapred/ClientServiceDelegate` and its RPC `killTaskAttempt()` in `hadoop-mapreduce-client-app/src/main/java/org/apache/hadoop/mapreduce/v2/app/client/MRClientService`, 2) `createContainerAllocator()`, `MRAppContainerAllocator()` and its `handle()` in `hadoop-mapreduce-client-app/src/test/java/org/apache/hadoop/mapreduce/v2/app/MRApp.java`. 

The **root cause function** is `MRClientService.killTaskAttempt()` and `MRAppContainerAllocator.handle()`

**Why:** When a `TaskAttempt` in state `UNASSIGNED`, if `MRAppContainerAllocator.handle()` runs first, it transits to `Assigned`, then `MRClientService.killTaskAttempt()` runs, `TaskAttempt` receives `TA_DIAGNOSTICS_UPDATE` and `TA_KILL` and transits to `Assigned` and `KILLED`. However, if `MRClientService.killTaskAttempt()` runs first, `TaskAttempt` receives `TA_DIAGNOSTICS_UPDATE` and `TA_KILL` but it still in state `UNASSIGNED`, where it cannot handle `TA_DIAGNOSTICS_UPDATE`, which raises an exception.

### MP-3274
Run `replcate.sh`.

The **error message function** is L64 of `nmHang()` in `hadoop-mapreduce-client-app/src/test/java/org/apache/hadoop/mapred/TestTaskAttemptListenerImpl`.  

The **crime function** is 1) `getTask` in `hadoop-mapreduce-client-app/src/main/java/org/apache/hadoop/mapred/TaskAttemptListenerImpl`, 2) `unregister()` in `hadoop-mapreduce-client-app/src/main/java/org/apache/hadoop/mapred/TaskAttemptListenerImpl`. 

The **root cause function** is `TaskAttemptListenerImpl.getTask()` and `TaskAttemptListenerImpl.unregister()`

**Why:** `TaskAttemptListenerImpl.unregister()` remove the element that `TaskAttemptListenerImpl.getTask()` try to visit.

### ZK-1144
Replication TBD

The **error message function** Not sure  

The **crime function** is 1) `lead()` in `src/java/main/org/apache/zookeeper/server/quorum/Leader`, 2) `run()` in `src/java/main/org/apache/zookeeper/server/quorum/LearnerHandler` and its callee `processAck()` in `src/java/main/org/apache/zookeeper/server/quorum/Leader`. 

The **root cause function** is `Leader.lead()` and `Leader.processAck()`

**Why:** `Leader.lead()` insert the element that `TaskAttemptListenerImpl.getTask()` try to visit. `outstandingProposals` is important. Similar to MP-3274.
