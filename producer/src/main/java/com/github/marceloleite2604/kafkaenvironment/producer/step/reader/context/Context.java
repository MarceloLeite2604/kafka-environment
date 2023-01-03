package com.github.marceloleite2604.kafkaenvironment.producer.step.reader.context;

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

  private int itemsRead;

  private int totalItems;

  public void incrementItemsRead() {
    itemsRead++;
  }

  @UtilityClass
  public static class Properties {
    public static final String ITEMS_READ = "items-read";
    public static final String TOTAL_ITEMS = "total-items";
  }
}
