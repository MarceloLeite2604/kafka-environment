package com.github.marceloleite2604.kafkaenvironment.producer.step;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum Step {
  USERS_RETRIEVAL("Users Retrieval Step"),
  ADDRESSES_RETRIEVAL("Addresses Retrieval Step");

  private final String name;
}
