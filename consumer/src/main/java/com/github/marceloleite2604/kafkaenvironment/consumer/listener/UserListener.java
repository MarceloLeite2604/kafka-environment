package com.github.marceloleite2604.kafkaenvironment.consumer.listener;

import com.github.marceloleite2604.kafkaenvironment.consumer.domain.user.User;
import com.github.marceloleite2604.kafkaenvironment.consumer.domain.user.dto.UserDto;
import com.github.marceloleite2604.kafkaenvironment.consumer.mapper.UserToDtoMapper;
import com.github.marceloleite2604.kafkaenvironment.consumer.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserListener {

  private final UserToDtoMapper userToDtoMapper;

  private final UserService userService;

  @KafkaListener(topics = "${consumer.kafka.topics.users}")
  public void listenUserTopic(UserDto userDto) {

    Mono.just(userDto)
        .map(userToDtoMapper::mapFrom)
        .flatMap(userService::save)
        .map(this::logConclusion)
        .subscribe();
  }

  private User logConclusion(User user) {
    log.info("User: {}", user);
    return user;
  }
}
