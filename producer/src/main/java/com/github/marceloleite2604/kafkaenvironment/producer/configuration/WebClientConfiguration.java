package com.github.marceloleite2604.kafkaenvironment.producer.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfiguration {

  @Bean(BeanNames.RANDOM_USER_WEB_CLIENT)
  public WebClient createRandomUserWebClient(WebClient.Builder webClientBuilder) {
    return webClientBuilder
        .baseUrl("http://randomuser.me/api")
        .build();
  }
}
