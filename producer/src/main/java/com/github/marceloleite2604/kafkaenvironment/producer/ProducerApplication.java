package com.github.marceloleite2604.kafkaenvironment.producer;

import com.github.marceloleite2604.kafkaenvironment.producer.properties.UserRetrievalJobProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
@EnableConfigurationProperties
@ConfigurationPropertiesScan(basePackageClasses = UserRetrievalJobProperties.class)
public class ProducerApplication {

  public static void main(String[] args) {
    System.exit(SpringApplication.exit(SpringApplication.run(ProducerApplication.class, args)));
  }
}
