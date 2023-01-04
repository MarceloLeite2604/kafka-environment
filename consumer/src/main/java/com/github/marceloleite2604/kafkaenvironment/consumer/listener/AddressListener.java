package com.github.marceloleite2604.kafkaenvironment.consumer.listener;

import com.github.marceloleite2604.kafkaenvironment.consumer.domain.address.Address;
import com.github.marceloleite2604.kafkaenvironment.consumer.domain.address.dto.AddressDto;
import com.github.marceloleite2604.kafkaenvironment.consumer.domain.user.User;
import com.github.marceloleite2604.kafkaenvironment.consumer.mapper.AddressToDtoMapper;
import com.github.marceloleite2604.kafkaenvironment.consumer.service.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@RequiredArgsConstructor
public class AddressListener {

  private final AddressToDtoMapper addressToDtoMapper;

  private final AddressService addressService;

  @KafkaListener(
      topics = "${consumer.kafka.topics.addresses}",
      clientIdPrefix = "${spring.kafka.consumer.client-id}-addresses")
  public void listenUserTopic(AddressDto addressDto) {

    Mono.just(addressDto)
        .map(addressToDtoMapper::mapFrom)
        .flatMap(addressService::save)
        .map(this::logConclusion)
        .subscribe();
  }

  private Address logConclusion(Address address) {
    log.debug("Address: {}", address);
    return address;
  }
}
