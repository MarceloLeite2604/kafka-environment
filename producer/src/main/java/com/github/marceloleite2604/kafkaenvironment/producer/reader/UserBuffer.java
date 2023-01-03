package com.github.marceloleite2604.kafkaenvironment.producer.reader;

import java.util.Iterator;

import com.github.marceloleite2604.kafkaenvironment.producer.domain.user.User;
import com.github.marceloleite2604.kafkaenvironment.producer.properties.UserRetrievalJobProperties;
import com.github.marceloleite2604.kafkaenvironment.producer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserBuffer extends ItemBuffer<User> {

  private final UserRetrievalJobProperties userRetrievalJobProperties;

  private final UserService userService;

  @Override
  protected Iterator<User> retrieveIterator() {
    return userService.retrieve(userRetrievalJobProperties.bufferedUsers())
        .iterator();
  }
}
