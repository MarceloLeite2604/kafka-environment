package com.github.marceloleite2604.kafkaenvironment.producer;

import com.github.marceloleite2604.kafkaenvironment.producer.properties.UsersRetrievalStepProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
@ConfigurationPropertiesScan(basePackageClasses = UsersRetrievalStepProperties.class)
public class ProducerApplication {

  public static void main(String[] args) {
    System.exit(SpringApplication.exit(SpringApplication.run(ProducerApplication.class, args)));
  }
}
