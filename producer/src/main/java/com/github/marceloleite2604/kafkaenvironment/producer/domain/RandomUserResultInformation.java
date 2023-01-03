package com.github.marceloleite2604.kafkaenvironment.producer.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@NoArgsConstructor(force = true)
public class RandomUserResultInformation {

  private final String seed;

  private final int results;

  private final int page;

  private final String version;
}
