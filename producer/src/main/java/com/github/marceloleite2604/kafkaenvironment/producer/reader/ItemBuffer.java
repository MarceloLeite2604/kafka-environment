package com.github.marceloleite2604.kafkaenvironment.producer.reader;

import java.util.Iterator;

public abstract class ItemBuffer<T> {

  private Iterator<T> iterator;

  public T read() {

    if (iterator == null || !iterator.hasNext()) {
      iterator = retrieveIterator();
    }

    if (!iterator.hasNext()) {
      throw new IllegalStateException("Item iterator is empty.");
    }

    return iterator.next();
  }

  protected abstract Iterator<T> retrieveIterator();
}
