package com.github.marceloleite2604.kafkaenvironment.consumer.mapper;

import com.github.marceloleite2604.kafkaenvironment.consumer.domain.address.Address;
import com.github.marceloleite2604.kafkaenvironment.consumer.domain.address.dto.AddressDto;
import com.github.marceloleite2604.kafkaenvironment.consumer.domain.address.dto.LocationDto;
import com.github.marceloleite2604.kafkaenvironment.consumer.domain.address.dto.StreetDto;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AddressToDtoMapper implements Mapper<Address, AddressDto> {

  @Override
  public Address mapFrom(AddressDto addressDto) {

    if (addressDto == null) {
      return null;
    }

    final var optionalLocation = Optional.ofNullable(addressDto.getLocation());
    final var optionalStreet = optionalLocation.map(LocationDto::getStreet);

    final var streetName = optionalStreet.map(StreetDto::getName)
        .orElse(null);

    final var streetNumber = optionalStreet.map(StreetDto::getNumber)
        .orElse(null);

    final var city = optionalLocation.map(LocationDto::getCity)
        .orElse(null);

    final var country = optionalLocation.map(LocationDto::getCountry)
        .orElse(null);

    final var state = optionalLocation.map(LocationDto::getState)
        .orElse(null);

    return Address.builder()
        .streetName(streetName)
        .streetNumber(streetNumber)
        .city(city)
        .country(country)
        .state(state)
        .build();
  }
}
