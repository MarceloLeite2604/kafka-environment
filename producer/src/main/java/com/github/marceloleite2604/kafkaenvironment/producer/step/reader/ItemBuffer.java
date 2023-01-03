package com.github.marceloleite2604.kafkaenvironment.producer.step.reader;

import java.util.Iterator;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class ItemBuffer<T> {

  private final int bufferSize;

  private Iterator<T> iterator;

  public T read() {

    if (iterator == null || !iterator.hasNext()) {
      iterator = retrieveIterator(bufferSize);
    }

    if (iterator == null) {
      throw new IllegalStateException("Item iterator is null.");
    }

    if (!iterator.hasNext()) {
      throw new IllegalStateException("Item iterator is empty.");
    }

    return iterator.next();
  }

  protected abstract Iterator<T> retrieveIterator(int bufferSize);
}
