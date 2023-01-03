package com.github.marceloleite2604.kafkaenvironment.producer.configuration.job.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class JobListener implements JobExecutionListener {

  private final JobOperator jobOperator;

  @Override
  public void beforeJob(@NonNull JobExecution jobExecution) {

    final var stopJobThread = new StopJobThread(jobOperator, jobExecution);

    Runtime.getRuntime()
        .addShutdownHook(stopJobThread);
  }
}
