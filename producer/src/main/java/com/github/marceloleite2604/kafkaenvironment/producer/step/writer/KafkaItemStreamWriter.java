package com.github.marceloleite2604.kafkaenvironment.producer.step.writer;

import com.github.marceloleite2604.kafkaenvironment.producer.domain.KafkaTopic;
import com.github.marceloleite2604.kafkaenvironment.producer.properties.ItemsRetrievalStepProperties;
import com.github.marceloleite2604.kafkaenvironment.producer.properties.KafkaProperties;
import com.github.marceloleite2604.kafkaenvironment.producer.step.context.Context;
import com.github.marceloleite2604.kafkaenvironment.producer.step.context.ContextWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamWriter;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
@StepScope
@Slf4j
public abstract class KafkaItemStreamWriter<T> implements ItemStreamWriter<T> {

  private final KafkaTemplate<String, T> kafkaTemplate;

  private final String topic;

  private final ItemsRetrievalStepProperties itemsRetrievalStepProperties;

  private Context context;

  protected KafkaItemStreamWriter(
      KafkaTemplate<String, T> kafkaTemplate,
      KafkaProperties kafkaProperties,
      KafkaTopic kafkaTopic,
      ItemsRetrievalStepProperties itemsRetrievalStepProperties) {
    this.kafkaTemplate = kafkaTemplate;
    this.itemsRetrievalStepProperties = itemsRetrievalStepProperties;
    this.topic = kafkaProperties.topics()
        .get(kafkaTopic);
  }

  @Override
  public void write(Chunk<? extends T> chunk) {
    chunk.iterator()
        .forEachRemaining(this::sendToKafka);

    context.incrementItemsManipulated(chunk.size());

    log.debug("{} items(s) sent to Kafka \"{}\" topic ({} out of {}).", chunk.size(), topic,
        context.getItemsManipulated(), context.getTotalItems());
  }

  private void sendToKafka(T item) {
    kafkaTemplate.send(topic, item);
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
