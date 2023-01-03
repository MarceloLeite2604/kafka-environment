package com.github.marceloleite2604.kafkaenvironment.producer.entity;

import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@NoArgsConstructor(force = true)
public class RandomUserResponse<T> {

  private final List<T> results;

  private final RandomUserResultInformation info;
}
