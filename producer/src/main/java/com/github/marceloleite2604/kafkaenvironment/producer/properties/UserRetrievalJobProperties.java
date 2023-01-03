package com.github.marceloleite2604.kafkaenvironment.producer.properties;

import jakarta.validation.constraints.Positive;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(PropertiesPath.USER_RETRIEVAL_JOB)
public record UserRetrievalJobProperties(
    @Positive int totalUsers,
    @Positive int chunkSize,
    @Positive int bufferedUsers) {
}
