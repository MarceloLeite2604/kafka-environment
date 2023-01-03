package com.github.marceloleite2604.kafkaenvironment.producer.step.writer;

import com.github.marceloleite2604.kafkaenvironment.producer.domain.KafkaTopic;
import com.github.marceloleite2604.kafkaenvironment.producer.properties.KafkaProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemStreamWriter;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@StepScope
@Slf4j
public abstract class KafkaItemStreamWriter<T> implements ItemStreamWriter<T> {

  private final KafkaTemplate<String, T> kafkaTemplate;

  private final String topic;

  protected KafkaItemStreamWriter(
      KafkaTemplate<String, T> kafkaTemplate,
      KafkaProperties kafkaProperties,
      KafkaTopic kafkaTopic) {
    this.kafkaTemplate = kafkaTemplate;
    this.topic = kafkaProperties.topics()
        .get(kafkaTopic);
  }

  @Override
  public void write(Chunk<? extends T> chunk) {
    chunk.iterator()
        .forEachRemaining(this::sendToKafka);
    log.debug("{} items(s) sent to Kafka \"{}\" topic.", chunk.size(), topic);
  }

  private void sendToKafka(T item) {
    kafkaTemplate.send(topic, item);
  }
}
