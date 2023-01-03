package com.github.marceloleite2604.kafkaenvironment.producer.configuration.job.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.launch.JobExecutionNotRunningException;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.NoSuchJobExecutionException;
import sun.misc.Signal;
import sun.misc.SignalHandler;

@RequiredArgsConstructor
@Slf4j
public class StopJobInterruptSignalHandler implements SignalHandler {

  private final JobOperator jobOperator;

  private final JobExecution jobExecution;

  @Override
  public void handle(Signal signal) {
    if ("INT".equals(signal.getName())) {
      log.info("Interrupted signal received. Stopping job instance \"{}\" execution {}.", jobExecution.getJobInstance()
          .getJobName(), jobExecution.getId());
      try {
        if (!jobOperator.stop(jobExecution.getId())) {
          log.warn("Could not stop job instance \"{}\" execution {}.", jobExecution.getJobInstance()
              .getJobName(), jobExecution.getId());
        }
      } catch (NoSuchJobExecutionException | JobExecutionNotRunningException exception) {
        log.error("Exception thrown while stopping job instance \"{}\" execution {}.", jobExecution.getJobInstance()
            .getJobName(), jobExecution.getId());
      }
    }
  }
}
