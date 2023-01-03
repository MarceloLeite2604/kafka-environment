package com.github.marceloleite2604.kafkaenvironment.producer.configuration.job.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.launch.JobExecutionNotRunningException;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.NoSuchJobExecutionException;

@RequiredArgsConstructor
@Slf4j
public class StopJobThread extends Thread {

  private final JobOperator jobOperator;

  private final JobExecution jobExecution;

  @Override
  public void run() {
    log.debug("Executing stop job thread.");
    if (jobExecution.isRunning()) {
      log.debug("Job is running.");
      try {
        if (!jobOperator.stop(jobExecution.getId())) {
          log.warn("Could not stop job instance \"{}\" execution {}.", jobExecution.getJobInstance()
              .getJobName(), jobExecution.getId());
        } else {
          log.debug("Job stopped successfully.");
        }
      } catch (NoSuchJobExecutionException | JobExecutionNotRunningException exception) {
        log.error("Exception thrown while stopping job instance \"{}\" execution {}.",
            jobExecution.getJobInstance()
                .getJobName(), jobExecution.getId());
      }
    }
  }
}
