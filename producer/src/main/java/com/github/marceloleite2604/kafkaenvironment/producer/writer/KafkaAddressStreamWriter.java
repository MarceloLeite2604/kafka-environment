package com.github.marceloleite2604.kafkaenvironment.producer.writer;

import com.github.marceloleite2604.kafkaenvironment.producer.domain.KafkaTopic;
import com.github.marceloleite2604.kafkaenvironment.producer.domain.address.Address;
import com.github.marceloleite2604.kafkaenvironment.producer.domain.user.User;
import com.github.marceloleite2604.kafkaenvironment.producer.properties.KafkaProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@StepScope
@Slf4j
public class KafkaAddressStreamWriter extends KafkaItemStreamWriter<Address> {
  public KafkaAddressStreamWriter(KafkaTemplate<String, Address> kafkaTemplate, KafkaProperties kafkaProperties) {
    super(kafkaTemplate, kafkaProperties, KafkaTopic.ADDRESSES);
  }
}
