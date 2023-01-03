package com.github.marceloleite2604.kafkaenvironment.producer.writer;

import com.github.marceloleite2604.kafkaenvironment.producer.domain.KafkaTopic;
import com.github.marceloleite2604.kafkaenvironment.producer.domain.user.User;
import com.github.marceloleite2604.kafkaenvironment.producer.properties.KafkaProperties;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemStreamWriter;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@StepScope
@Slf4j
public class KafkaUserStreamWriter extends KafkaItemStreamWriter<User> {

  public KafkaUserStreamWriter(KafkaTemplate<String, User> kafkaTemplate, KafkaProperties kafkaProperties) {
    super(kafkaTemplate, kafkaProperties, KafkaTopic.USERS);
  }
}
