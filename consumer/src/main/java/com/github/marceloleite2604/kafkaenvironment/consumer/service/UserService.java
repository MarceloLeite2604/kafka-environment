package com.github.marceloleite2604.kafkaenvironment.consumer.service;

import com.github.marceloleite2604.kafkaenvironment.consumer.domain.user.User;
import com.github.marceloleite2604.kafkaenvironment.consumer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public Mono<User> save(User user) {
    return userRepository.save(user);
  }

  public Mono<User> save(Mono<User> userMono) {
    return userMono.flatMap(userRepository::save);
  }
}
