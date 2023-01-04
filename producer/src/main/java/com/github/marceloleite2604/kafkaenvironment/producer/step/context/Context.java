package com.github.marceloleite2604.kafkaenvironment.producer.step.context;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.UtilityClass;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@AllArgsConstructor
@Getter
@ToString
public class Context {

  private int itemsManipulated;

  private int totalItems;

  public void incrementItemsManipulated() {
    itemsManipulated++;
  }

  public void incrementItemsManipulated(int amount) {
    itemsManipulated += amount;
  }

  @UtilityClass
  public static class Properties {
    public static final String ITEMS_MANIPULATED = "items-manipulated";
    public static final String TOTAL_ITEMS = "total-items";
  }
}
