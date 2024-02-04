# Bug Data
 Replication of train-ticket and collect logs, traces and metrics.

## Done
### MP-4637
Run `replcate.sh`.

For buggy run, comment L597 in `hadoop-mapreduce-client-app/src/test/java/org/apache/hadoop/mapreduce/v2/app/job/impl/TestTaskAttempt.java`. For normal run, activate L597.

The **error message function** is L982 of `handle()` in `hadoop-mapreduce-project/hadoop-mapreduce-client/hadoop-mapreduce-client-app/src/main/java/org/apache/hadoop/mapreduce/v2/app/job/impl/TaskAttemptImpl`.  

The **crime function** is `killTaskAttempt()` in `hadoop-mapreduce-client-app/src/main/java/org/apache/hadoop/mapreduce/v2/app/client/MRClientService`. 

The **root cause function** is L318 `app.Context.getEventHandler().handle(new TaskAttemptDiagnosticsUpdateEvent(taskAttemptId, message))` and L320 `app.Context.getEventHandler().handle(new TaskAttemptEvent(taskAttemptId, TaskAttemptEventType.TA_KILL))`. 

**Why:** When a `TaskAttempt` in state `UNASSIGNED`, if it receive `TA_KILL` then `TA_DIAGNOSTICS_UPDATE`, it wil translate to `KILLED` and stay here without error. However, if it receive `TA_DIAGNOSTICS_UPDATE` first, `UNASSIGNED` state cannot process this translation message and will raise exception.
