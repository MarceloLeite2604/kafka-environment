package com.github.marceloleite2604.kafkaenvironment.producer.properties;

import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Range;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public abstract class ItemsRetrievalStepProperties {

  @Positive
  private final int totalItems;

  @Positive
  private final int chunkSize;

  @Positive
  private final int bufferedItems;

  @Range(max = 1L)
  private final float failureChanceRate;
}
