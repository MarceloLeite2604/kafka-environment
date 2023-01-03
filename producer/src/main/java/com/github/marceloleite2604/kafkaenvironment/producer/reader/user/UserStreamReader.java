package com.github.marceloleite2604.kafkaenvironment.producer.reader.user;

import com.github.marceloleite2604.kafkaenvironment.producer.domain.user.User;
import com.github.marceloleite2604.kafkaenvironment.producer.properties.UsersRetrievalJobProperties;
import com.github.marceloleite2604.kafkaenvironment.producer.reader.BufferedItemStreamReader;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class UserStreamReader extends BufferedItemStreamReader<User> {
  public UserStreamReader(
      UsersRetrievalJobProperties userRetrievalJobProperties,
      UserBuffer userBuffer) {
    super(userRetrievalJobProperties, userBuffer);
  }
}
