package com.github.marceloleite2604.kafkaenvironment.consumer.properties;

import com.github.marceloleite2604.kafkaenvironment.consumer.domain.KafkaTopic;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.util.Map;

@Validated
@ConfigurationProperties(PropertiesPath.KAFKA)
public record KafkaProperties(@NotEmpty Map<KafkaTopic, String> topics) {}
