package com.github.marceloleite2604.kafkaenvironment.producer.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.marceloleite2604.kafkaenvironment.producer.domain.user.User;
import com.github.marceloleite2604.kafkaenvironment.producer.properties.UserRetrievalJobProperties;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class UserRetrievalStepConfiguration {

  @Bean(BeanNames.USER_RETRIEVAL_STEP)
  public Step createUserRetrievalStep(
      JobRepository jobRepository,
      PlatformTransactionManager platformTransactionManager,
      UserRetrievalJobProperties userRetrievalJobProperties,
      ItemReader<User> userStreamReader,
      ItemWriter<User> kafkaUserStreamWriter,
      ObjectMapper objectMapper) {

    return new StepBuilder("User retrieval", jobRepository).<User, User>chunk(userRetrievalJobProperties.chunkSize(),
            platformTransactionManager)
        .reader(userStreamReader)
        .writer(kafkaUserStreamWriter)
        .build();
  }

}
