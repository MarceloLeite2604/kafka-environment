package com.github.marceloleite2604.kafkaenvironment.consumer.mapper;

import java.util.Optional;

import com.github.marceloleite2604.kafkaenvironment.consumer.domain.address.Address;
import com.github.marceloleite2604.kafkaenvironment.consumer.domain.address.dto.AddressDto;
import com.github.marceloleite2604.kafkaenvironment.consumer.domain.address.dto.StreetDto;
import org.springframework.stereotype.Component;

@Component
public class AddressToDtoMapper implements Mapper<Address, AddressDto> {

  @Override
  public Address mapFrom(AddressDto addressDto) {

    if (addressDto == null) {
      return null;
    }

    final var optionalStreet = Optional.ofNullable(addressDto.getStreet());

    final var streetName = optionalStreet.map(StreetDto::getName)
        .orElse(null);

    final var streetNumber = optionalStreet.map(StreetDto::getNumber)
        .orElse(null);

    return Address.builder()
        .streetName(streetName)
        .streetNumber(streetNumber)
        .city(addressDto.getCity())
        .country(addressDto.getCountry())
        .state(addressDto.getState())
        .build();
  }
}
