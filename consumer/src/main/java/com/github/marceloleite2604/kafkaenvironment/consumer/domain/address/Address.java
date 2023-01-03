package com.github.marceloleite2604.kafkaenvironment.consumer.domain.address;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Address {
  private final Street street;

  private final String city;

  private final String state;

  private final String country;
}
