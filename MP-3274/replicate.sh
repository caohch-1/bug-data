javac ./hadoop-mapreduce-client-app/src/test/java/org/apache/hadoop/mapred/TestTaskAttemptListenerImpl.java\
 ./hadoop-mapreduce-client-app/src/main/java/org/apache/hadoop/mapred/TaskAttemptListenerImpl.java\
 ./hadoop-mapreduce-client-app/src/main/java/org/apache/hadoop/mapreduce/v2/app/TaskAttemptListener.java\
 ./hadoop-mapreduce-client-app/src/main/java/org/apache/hadoop/mapreduce/v2/app/job/impl/TaskAttemptImpl.java\
 ./hadoop-mapreduce-client-app/src/main/java/org/apache/hadoop/mapreduce/v2/app/TaskHeartbeatHandler.java -cp \
:/Users/caohch1/Projects/bug-data/MP-3274/dependency/commons-logging-1.1.1.jar\
:/Users/caohch1/Projects/bug-data/MP-3274/dependency/junit-4.10.jar\
:/Users/caohch1/Projects/bug-data/MP-3274/dependency/mockito-all-1.8.5.jar\
:/Users/caohch1/Projects/bug-data/MP-3274/dependency/hadoop-common-2.0.0-alpha.jar\
:/Users/caohch1/Projects/bug-data/MP-3274/dependency/hadoop-mapreduce-client-common-2.0.0-alpha.jar\
:/Users/caohch1/Projects/bug-data/MP-3274/dependency/hadoop-mapreduce-client-core-2.0.0-alpha.jar\
:/Users/caohch1/Projects/bug-data/MP-3274/dependency/hadoop-mapreduce-client-jobclient-2.0.0-alpha.jar\
:/Users/caohch1/Projects/bug-data/MP-3274/dependency/hadoop-mapreduce-client-app-2.0.0-alpha.jar\
:/Users/caohch1/Projects/bug-data/MP-3274/dependency/hadoop-mapreduce-client-shuffle-2.0.0-alpha.jar\
:/Users/caohch1/Projects/bug-data/MP-3274/dependency/hadoop-mapreduce-client-hs-2.0.0-alpha.jar\
:/Users/caohch1/Projects/bug-data/MP-3274/dependency/hadoop-yarn-common-2.0.0-alpha.jar\
:/Users/caohch1/Projects/bug-data/MP-3274/dependency/hadoop-yarn-server-common-2.0.0-alpha.jar\
:/Users/caohch1/Projects/bug-data/MP-3274/dependency/hadoop-yarn-server-nodemanager-2.0.0-alpha.jar\
:/Users/caohch1/Projects/bug-data/MP-3274/dependency/hadoop-yarn-server-resourcemanager-2.0.0-alpha.jar\
:/Users/caohch1/Projects/bug-data/MP-3274/dependency/hadoop-yarn-server-web-proxy-2.0.0-alpha.jar\
:/Users/caohch1/Projects/bug-data/MP-3274/dependency/hadoop-yarn-api-2.0.0-alpha.jar

java -cp .\
:./hadoop-mapreduce-client-app/src/main/java/\
:./hadoop-mapreduce-client-app/src/test/java/\
:/Users/caohch1/Projects/bug-data/MP-3274/dependency/junit-4.10.jar\
:/Users/caohch1/Projects/bug-data/MP-3274/dependency/netty-3.2.10.Final.jar\
:/Users/caohch1/Projects/bug-data/MP-3274/dependency/avro-1.7.0.jar\
:/Users/caohch1/Projects/bug-data/MP-3274/dependency/mockito-all-1.8.5.jar\
:/Users/caohch1/Projects/bug-data/MP-3274/dependency/commons-logging-1.1.1.jar\
:/Users/caohch1/Projects/bug-data/MP-3274/dependency/slf4j-api-1.7.0.jar\
:/Users/caohch1/Projects/bug-data/MP-3274/dependency/slf4j-simple-1.7.0.jar\
:/Users/caohch1/Projects/bug-data/MP-3274/dependency/mockito-all-1.8.5.jar\
:/Users/caohch1/Projects/bug-data/MP-3274/dependency/guava-15.0.jar\
:/Users/caohch1/Projects/bug-data/MP-3274/dependency/protobuf-java-2.6.0.jar\
:/Users/caohch1/Projects/bug-data/MP-3274/dependency/log4j-1.2.17.jar\
:/Users/caohch1/Projects/bug-data/MP-3274/dependency/hadoop-common-2.0.0-alpha.jar\
:/Users/caohch1/Projects/bug-data/MP-3274/dependency/commons-configuration-1.10.jar\
:/Users/caohch1/Projects/bug-data/MP-3274/dependency/commons-lang-2.6.jar\
:/Users/caohch1/Projects/bug-data/MP-3274/dependency/hadoop-auth-2.0.0-alpha.jar\
:/Users/caohch1/Projects/bug-data/MP-3274/dependency/hadoop-mapreduce-client-common-2.0.0-alpha.jar\
:/Users/caohch1/Projects/bug-data/MP-3274/dependency/hadoop-mapreduce-client-core-2.0.0-alpha.jar\
:/Users/caohch1/Projects/bug-data/MP-3274/dependency/hadoop-mapreduce-client-jobclient-2.0.0-alpha.jar\
:/Users/caohch1/Projects/bug-data/MP-3274/dependency/hadoop-mapreduce-client-app-2.0.0-alpha.jar\
:/Users/caohch1/Projects/bug-data/MP-3274/dependency/hadoop-mapreduce-client-shuffle-2.0.0-alpha.jar\
:/Users/caohch1/Projects/bug-data/MP-3274/dependency/hadoop-mapreduce-client-hs-2.0.0-alpha.jar\
:/Users/caohch1/Projects/bug-data/MP-3274/dependency/hadoop-yarn-common-2.0.0-alpha.jar\
:/Users/caohch1/Projects/bug-data/MP-3274/dependency/hadoop-yarn-server-common-2.0.0-alpha.jar\
:/Users/caohch1/Projects/bug-data/MP-3274/dependency/hadoop-yarn-server-nodemanager-2.0.0-alpha.jar\
:/Users/caohch1/Projects/bug-data/MP-3274/dependency/hadoop-yarn-server-resourcemanager-2.0.0-alpha.jar\
:/Users/caohch1/Projects/bug-data/MP-3274/dependency/hadoop-yarn-server-web-proxy-2.0.0-alpha.jar\
:/Users/caohch1/Projects/bug-data/MP-3274/dependency/hadoop-yarn-api-2.0.0-alpha.jar\
 org.junit.runner.JUnitCore  org.apache.hadoop.mapred.TestTaskAttemptListenerImpl