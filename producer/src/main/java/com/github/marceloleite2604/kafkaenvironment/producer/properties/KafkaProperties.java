package com.github.marceloleite2604.kafkaenvironment.producer.properties;

import java.util.Map;

import com.github.marceloleite2604.kafkaenvironment.producer.domain.KafkaTopic;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(PropertiesPath.KAFKA)
public record KafkaProperties(
    @NotEmpty Map<KafkaTopic, String> topics
) {}
