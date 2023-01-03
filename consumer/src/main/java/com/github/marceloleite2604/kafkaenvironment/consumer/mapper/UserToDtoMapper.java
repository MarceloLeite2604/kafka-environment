package com.github.marceloleite2604.kafkaenvironment.consumer.mapper;

import java.util.Optional;

import com.github.marceloleite2604.kafkaenvironment.consumer.domain.user.User;
import com.github.marceloleite2604.kafkaenvironment.consumer.domain.user.dto.NameDto;
import com.github.marceloleite2604.kafkaenvironment.consumer.domain.user.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserToDtoMapper implements Mapper<User, UserDto> {

  @Override
  public User mapFrom(UserDto userDto) {

    if (userDto == null) {
      return null;
    }

    final var optionalName = Optional.ofNullable(userDto.getName());

    final var firstName = optionalName.map(NameDto::getFirst)
        .orElse(null);

    final var lastName = optionalName.map(NameDto::getLast)
        .orElse(null);

    final var email = userDto.getEmail();

    return User.builder()
        .firstName(firstName)
        .lastName(lastName)
        .email(email)
        .build();
  }
}
