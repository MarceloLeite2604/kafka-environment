package com.github.marceloleite2604.kafkaenvironment.consumer.repository;

import com.github.marceloleite2604.kafkaenvironment.consumer.domain.address.Address;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface AddressRepository extends ReactiveMongoRepository<Address, String> {
}
