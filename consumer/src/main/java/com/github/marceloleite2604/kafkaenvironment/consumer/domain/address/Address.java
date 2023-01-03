package com.github.marceloleite2604.kafkaenvironment.consumer.domain.address;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("addresses")
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
public class Address {

  @ToString.Exclude
  @Id
  private final String id;

  private final String streetName;

  private final Integer streetNumber;

  private final String city;

  private final String state;

  private final String country;
}
