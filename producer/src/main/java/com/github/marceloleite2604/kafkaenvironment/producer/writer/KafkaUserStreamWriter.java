package com.github.marceloleite2604.kafkaenvironment.producer.writer;

import com.github.marceloleite2604.kafkaenvironment.producer.configuration.KafkaConfiguration;
import com.github.marceloleite2604.kafkaenvironment.producer.domain.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemStreamWriter;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@StepScope
@RequiredArgsConstructor
@Slf4j
public class KafkaUserStreamWriter implements ItemStreamWriter<User> {

  private final KafkaTemplate<String, User> kafkaTemplate;

  @Override
  public void write(Chunk<? extends User> chunk) {
    chunk.iterator()
        .forEachRemaining(this::sendToKafka);
    log.debug("{} user(s) sent to Kafka {} topic.", chunk.size(), KafkaConfiguration.Topic.USERS);
  }

  private void sendToKafka(User user) {
    kafkaTemplate.send(KafkaConfiguration.Topic.USERS, user);
  }
}
