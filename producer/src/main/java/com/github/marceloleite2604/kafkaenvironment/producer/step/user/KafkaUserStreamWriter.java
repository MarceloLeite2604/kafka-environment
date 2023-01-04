package com.github.marceloleite2604.kafkaenvironment.producer.step.user;

import com.github.marceloleite2604.kafkaenvironment.producer.domain.KafkaTopic;
import com.github.marceloleite2604.kafkaenvironment.producer.domain.user.User;
import com.github.marceloleite2604.kafkaenvironment.producer.properties.KafkaProperties;
import com.github.marceloleite2604.kafkaenvironment.producer.properties.UsersRetrievalStepProperties;
import com.github.marceloleite2604.kafkaenvironment.producer.step.writer.KafkaItemStreamWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@StepScope
@Slf4j
public class KafkaUserStreamWriter extends KafkaItemStreamWriter<User> {

  public KafkaUserStreamWriter(
      KafkaTemplate<String, User> kafkaTemplate,
      KafkaProperties kafkaProperties,
      UsersRetrievalStepProperties usersRetrievalStepProperties) {
    super(kafkaTemplate, kafkaProperties, KafkaTopic.USERS, usersRetrievalStepProperties);
  }
}
