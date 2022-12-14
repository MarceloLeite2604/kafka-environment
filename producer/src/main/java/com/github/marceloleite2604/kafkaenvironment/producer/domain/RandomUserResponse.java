package com.github.marceloleite2604.kafkaenvironment.producer.domain;

import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class RandomUserResponse<T> {

  private final List<T> results;

  private final RandomUserResultInformation info;
}
