package com.github.marceloleite2604.kafkaenvironment.producer.reader;

import com.github.marceloleite2604.kafkaenvironment.producer.properties.ItemsRetrievalJobProperties;
import org.springframework.batch.item.ItemStreamReader;

public abstract class BufferedItemStreamReader<T> implements ItemStreamReader<T> {

  private final int totalItems;

  private final ItemBuffer<T> itemBuffer;

  private int itemsRead = 0;

  protected BufferedItemStreamReader(ItemsRetrievalJobProperties itemsRetrievalJobProperties, ItemBuffer<T> itemBuffer) {
    this.totalItems = itemsRetrievalJobProperties.getTotalItems();
    this.itemBuffer = itemBuffer;
  }

  @Override
  public T read() {
    if (itemsRead >= totalItems) {
      return null;
    }

    itemsRead++;
    return itemBuffer.read();
  }
}
