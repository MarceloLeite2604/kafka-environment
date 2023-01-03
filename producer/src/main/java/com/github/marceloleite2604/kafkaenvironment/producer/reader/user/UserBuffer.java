package com.github.marceloleite2604.kafkaenvironment.producer.reader.user;

import java.util.Iterator;

import com.github.marceloleite2604.kafkaenvironment.producer.domain.user.User;
import com.github.marceloleite2604.kafkaenvironment.producer.properties.UsersRetrievalJobProperties;
import com.github.marceloleite2604.kafkaenvironment.producer.reader.ItemBuffer;
import com.github.marceloleite2604.kafkaenvironment.producer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserBuffer extends ItemBuffer<User> {

  private final UsersRetrievalJobProperties userRetrievalJobProperties;

  private final UserService userService;

  @Override
  protected Iterator<User> retrieveIterator() {
    return userService.retrieve(userRetrievalJobProperties.getBufferedItems())
        .iterator();
  }
}
