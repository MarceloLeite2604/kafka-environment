package com.github.marceloleite2604.kafkaenvironment.producer.step.reader;

import com.github.marceloleite2604.kafkaenvironment.producer.exception.SimulatedErrorException;
import com.github.marceloleite2604.kafkaenvironment.producer.properties.ItemsRetrievalStepProperties;
import com.github.marceloleite2604.kafkaenvironment.producer.step.context.Context;
import com.github.marceloleite2604.kafkaenvironment.producer.step.context.ContextWrapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.lang.NonNull;

import java.security.SecureRandom;
import java.util.Random;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Slf4j
public abstract class BufferedItemStreamReader<T> implements ItemStreamReader<T> {

  private final ItemsRetrievalStepProperties itemsRetrievalStepProperties;

  private final ItemBuffer<T> itemBuffer;

  private final Random random = new SecureRandom();

  private Context context;

  @Override
  public T read() {
    if (context.getItemsManipulated() >= context.getTotalItems()) {
      return null;
    }

    final var item = itemBuffer.read();
    context.incrementItemsManipulated();
    checkIfSimulatedErrorMustBeThrown();
    return item;
  }

  private void checkIfSimulatedErrorMustBeThrown() {
    if (random.nextFloat() <= itemsRetrievalStepProperties.getFailureChanceRate()) {
      log.debug("Throwing simulated error exception.");
      throw new SimulatedErrorException("Simulated error.");
    }
  }

  @Override
  public void open(@NonNull ExecutionContext executionContext) throws ItemStreamException {
    context = createContextWrapper(executionContext).restore();
  }

  @Override
  public void update(@NonNull ExecutionContext executionContext) throws ItemStreamException {
    createContextWrapper(executionContext).save();
  }

  private ContextWrapper createContextWrapper(ExecutionContext executionContext) {
    return ContextWrapper.builder()
        .itemsRetrievalStepProperties(itemsRetrievalStepProperties)
        .context(context)
        .executionContext(executionContext)
        .build();
  }
}
