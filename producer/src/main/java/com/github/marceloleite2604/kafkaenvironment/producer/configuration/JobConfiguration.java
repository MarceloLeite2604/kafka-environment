package com.github.marceloleite2604.kafkaenvironment.producer.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobConfiguration {

  @Bean(BeanNames.RETRIEVE_USERS_JOB)
  public Job createRetrieveUsersJob(
      JobRepository jobRepository,
      Step userRetrievalStep) {
    return new JobBuilder("Retrieve users", jobRepository)
        .incrementer(new RunIdIncrementer())
        .flow(userRetrievalStep)
        .end()
        .build();
  }
}
