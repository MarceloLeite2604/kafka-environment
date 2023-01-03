package com.github.marceloleite2604.kafkaenvironment.consumer.domain.user.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class NameDto {

  private final String first;

  private final String last;
}
