package com.github.marceloleite2604.kafkaenvironment.consumer.mapper;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

public interface Mapper<I, O> {

  default O mapTo(I value) {
    throw new UnsupportedOperationException("Method not implemented.");
  }

  default I mapFrom(O value) {
    throw new UnsupportedOperationException("Method not implemented.");
  }

  default Collection<O> mapAllTo(Collection<I> values) {

    if (values == null) {
      return Collections.emptyList();
    }

    return values.stream()
        .map(this::mapTo)
        .collect(Collectors.toList());
  }

  default Collection<I> mapAllFrom(Collection<O> values) {

    if (values == null) {
      return Collections.emptyList();
    }

    return values.stream()
        .map(this::mapFrom)
        .collect(Collectors.toList());
  }
}
