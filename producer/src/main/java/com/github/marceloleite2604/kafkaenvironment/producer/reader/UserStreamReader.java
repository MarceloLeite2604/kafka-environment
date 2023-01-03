package com.github.marceloleite2604.kafkaenvironment.producer.reader;

import com.github.marceloleite2604.kafkaenvironment.producer.domain.user.User;
import com.github.marceloleite2604.kafkaenvironment.producer.properties.UserRetrievalJobProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.stereotype.Component;

@Component
@StepScope
@RequiredArgsConstructor
public class UserStreamReader implements ItemStreamReader<User> {

  private final UserRetrievalJobProperties userRetrievalJobProperties;

  private final UserBuffer userBuffer;

  private int usersRead = 0;

  @Override
  public User read() {
    if (usersRead >= userRetrievalJobProperties.totalUsers()) {
      return null;
    }

    final var user = userBuffer.read();
    usersRead++;
    return user;
  }
}
