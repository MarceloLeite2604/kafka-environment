package com.github.marceloleite2604.kafkaenvironment.producer.properties;

import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Range;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public abstract class ItemsRetrievalStepProperties {

  /**
   * Total items to be retrieved by the step. Must be positive.
   */
  @Positive
  private final int totalItems;

  /**
   * Amount of items to be processed by each chunk. Must be positive.
   */
  @Positive
  private final int chunkSize;

  /**
   * How many items will be buffered for reading. Must be positive.
   */
  @Positive
  private final int bufferedItems;

  /**
   * The rate chance of a simulated failure while reading items. Must be a value between 0.0 and 1.0.
   */
  @Range(max = 1L)
  private final float failureChanceRate;
}
