package com.github.marceloleite2604.kafkaenvironment.consumer.service;

import com.github.marceloleite2604.kafkaenvironment.consumer.domain.address.Address;
import com.github.marceloleite2604.kafkaenvironment.consumer.domain.user.User;
import com.github.marceloleite2604.kafkaenvironment.consumer.repository.AddressRepository;
import com.github.marceloleite2604.kafkaenvironment.consumer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AddressService {

  private final AddressRepository addressRepository;

  public Mono<Address> save(Address address) {
    return addressRepository.save(address);
  }

  public Mono<Address> save(Mono<Address> addressMono) {
    return addressMono.flatMap(addressRepository::save);
  }
}
