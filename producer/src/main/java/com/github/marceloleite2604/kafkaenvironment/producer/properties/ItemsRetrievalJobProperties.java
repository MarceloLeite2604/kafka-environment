package com.github.marceloleite2604.kafkaenvironment.producer.properties;

import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public abstract class ItemsRetrievalJobProperties {

  @Positive
  private final int totalItems;

  @Positive
  private final int chunkSize;

  @Positive
  private final int bufferedItems;
}
