package com.github.marceloleite2604.kafkaenvironment.consumer.repository;

import com.github.marceloleite2604.kafkaenvironment.consumer.domain.user.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface UserRepository extends ReactiveMongoRepository<User, String> {
}
