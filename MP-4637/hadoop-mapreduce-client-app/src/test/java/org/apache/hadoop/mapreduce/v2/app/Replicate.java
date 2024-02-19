package org.apache.hadoop.mapreduce.v2.app;

import java.util.Iterator;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.v2.api.records.JobState;
import org.apache.hadoop.mapreduce.v2.api.records.TaskAttemptState;
import org.apache.hadoop.mapreduce.v2.api.records.TaskState;
import org.apache.hadoop.mapreduce.v2.app.client.ClientService;
import org.apache.hadoop.mapreduce.v2.app.client.MRClientService;
import org.apache.hadoop.mapreduce.v2.app.job.Job;
import org.apache.hadoop.mapreduce.v2.app.job.Task;
import org.apache.hadoop.mapreduce.v2.app.job.TaskAttempt;


public class Replicate {
    
  public static void main(String[] args) throws Exception {
    MRAppWithClientService app = new MRAppWithClientService(1, 0, false);
    // MRApp app = new MRApp(1, 0, false, "...", false);
    Configuration conf = new Configuration();
    Job job = app.submit(conf);
    // app.waitForState(job, JobState.RUNNING);
    // // Assert.assertEquals("Num tasks not correct", 1, job.getTasks().size());
    // Iterator<Task> it = job.getTasks().values().iterator();
    // Task task = it.next();
    // app.waitForState(task, TaskState.RUNNING);
    // TaskAttempt attempt = task.getAttempts().values().iterator().next();
    // app.waitForState(attempt, TaskAttemptState.RUNNING);

    // System.out.println("Debug: "+job.getState()+" "+task.getState()+" "+attempt.getState());
  }

}

class MRAppWithClientService extends MRApp {
    MRClientService clientService = null;
    MRAppWithClientService(int maps, int reduces, boolean autoComplete) {
      super(maps, reduces, autoComplete, "MRAppWithClientService", true);
    }
    @Override
    protected ClientService createClientService(AppContext context) {
      clientService = new MRClientService(context);
      return clientService;
    }
  }
