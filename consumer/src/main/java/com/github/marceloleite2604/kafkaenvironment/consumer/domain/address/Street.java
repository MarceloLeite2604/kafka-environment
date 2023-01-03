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
public class Street {

  private final String name;

  private final int number;
}
