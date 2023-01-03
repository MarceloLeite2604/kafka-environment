package com.github.marceloleite2604.kafkaenvironment.producer.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@NoArgsConstructor(force = true)
public class User {

  private final String firstName;

  private final String lastName;

  private final String email;
}
