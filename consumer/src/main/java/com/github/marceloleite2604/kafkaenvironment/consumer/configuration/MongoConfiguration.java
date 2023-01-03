package com.github.marceloleite2604.kafkaenvironment.consumer.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;

@Configuration
@RequiredArgsConstructor
public class MongoConfiguration implements InitializingBean {

  private final MappingMongoConverter mappingMongoConverter;

  @Override
  public void afterPropertiesSet() {
    mappingMongoConverter.setTypeMapper(new DefaultMongoTypeMapper(null));
  }
}
