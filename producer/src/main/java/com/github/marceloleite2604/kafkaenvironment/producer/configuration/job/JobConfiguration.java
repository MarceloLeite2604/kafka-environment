package com.github.marceloleite2604.kafkaenvironment.producer.configuration.job;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.marceloleite2604.kafkaenvironment.producer.configuration.BeanNames;
import com.github.marceloleite2604.kafkaenvironment.producer.configuration.job.listener.JobListener;
import com.github.marceloleite2604.kafkaenvironment.producer.domain.address.Address;
import com.github.marceloleite2604.kafkaenvironment.producer.domain.user.User;
import com.github.marceloleite2604.kafkaenvironment.producer.properties.AddressesRetrievalJobProperties;
import com.github.marceloleite2604.kafkaenvironment.producer.properties.UsersRetrievalJobProperties;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class JobConfiguration {

  @Bean(BeanNames.Job.Steps.USERS_RETRIEVAL)
  public Step createUsersRetrievalStep(
      JobRepository jobRepository,
      PlatformTransactionManager platformTransactionManager,
      UsersRetrievalJobProperties usersRetrievalJobProperties,
      ItemReader<User> userItemReader,
      ItemWriter<User> userItemWriter,
      ObjectMapper objectMapper) {

    return new StepBuilder("Users Retrieval Step", jobRepository).<User, User>chunk(
            usersRetrievalJobProperties.getChunkSize(),
            platformTransactionManager)
        .reader(userItemReader)
        .writer(userItemWriter)
        .build();
  }

  @Bean(BeanNames.Job.Steps.ADDRESSES_RETRIEVAL)
  public Step createAddressesRetrievalStep(
      JobRepository jobRepository,
      PlatformTransactionManager platformTransactionManager,
      AddressesRetrievalJobProperties addressRetrievalJobProperties,
      ItemReader<Address> addressItemReader,
      ItemWriter<Address> addressItemWriter,
      ObjectMapper objectMapper) {

    return new StepBuilder("Addresses Retrieval Step", jobRepository).<Address, Address>chunk(
            addressRetrievalJobProperties.getChunkSize(),
            platformTransactionManager)
        .reader(addressItemReader)
        .writer(addressItemWriter)
        .build();
  }

  @Bean(BeanNames.TASK_EXECUTOR)
  public TaskExecutor createTaskExecutor() {
    return new SimpleAsyncTaskExecutor("batch");
  }

  @Bean(BeanNames.Job.NAME)
  public Job createUsersRetrievalJob(
      JobRepository jobRepository,
      TaskExecutor taskExecutor,
      Step usersRetrievalStep,
      Step addressesRetrievalStep,
      JobListener jobListener) {

    final var usersRetrievalFlow = new FlowBuilder<SimpleFlow>("Users retrieval flow")
        .start(usersRetrievalStep)
        .build();

    final var addressesRetrievalFlow = new FlowBuilder<SimpleFlow>("Addresses retrieval flow")
        .start(addressesRetrievalStep)
        .build();

    final var splitFlow = new FlowBuilder<SimpleFlow>("Split flow")
        .split(taskExecutor)
        .add(usersRetrievalFlow, addressesRetrievalFlow)
        .build();

    return new JobBuilder("Users Retrieval Job", jobRepository)
        .start(splitFlow)
        .end()
        .listener(jobListener)
        .build();
  }

  @Bean(BeanNames.JOB_REGISTRY_BEAN_POST_PROCESSOR)
  public JobRegistryBeanPostProcessor createJobRegistryBeanPostProcessor(JobRegistry jobRegistry) {
    JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor = new JobRegistryBeanPostProcessor();
    jobRegistryBeanPostProcessor.setJobRegistry(jobRegistry);
    return jobRegistryBeanPostProcessor;
  }
}
