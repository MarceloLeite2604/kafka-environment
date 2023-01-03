package com.github.marceloleite2604.kafkaenvironment.consumer.domain.address.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class StreetDto {

  private final String name;

  private final int number;
}
