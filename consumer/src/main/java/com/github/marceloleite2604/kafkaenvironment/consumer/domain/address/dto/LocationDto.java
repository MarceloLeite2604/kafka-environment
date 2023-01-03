package com.github.marceloleite2604.kafkaenvironment.consumer.domain.address.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class LocationDto {
    private final StreetDto street;

    private final String city;

    private final String state;

    private final String country;
}
