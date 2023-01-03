package com.github.marceloleite2604.kafkaenvironment.producer.step.reader;

import java.net.URI;
import java.time.Duration;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import com.github.marceloleite2604.kafkaenvironment.producer.domain.ErrorResponse;
import com.github.marceloleite2604.kafkaenvironment.producer.domain.RandomUserResponse;
import com.github.marceloleite2604.kafkaenvironment.producer.domain.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.util.CollectionUtils;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

@Slf4j
public abstract class RandomUserItemBuffer<T> extends ItemBuffer<T> {

  private final WebClient randomUserWebClient;

  protected RandomUserItemBuffer(int bufferSize, WebClient randomUserWebClient) {
    super(bufferSize);
    this.randomUserWebClient = randomUserWebClient;
  }

  @Override
  protected Iterator<T> retrieveIterator(int amount) {
    return randomUserWebClient.get()
        .uri(uriBuilder -> buildUri(uriBuilder, amount))
        .retrieve()
        .onStatus(HttpStatusCode::isError, this::handleErrorResponse)
        .bodyToMono(retrieveParameterizedTypeReference())
        .retryWhen(Retry.backoff(3, Duration.ofSeconds(1)))
        .blockOptional()
        .map(RandomUserResponse::getResults)
        .map(List::iterator)
        .orElseThrow(() -> new IllegalStateException("Could not retrieve items from randomuser.me."));
  }

  private Mono<Exception> handleErrorResponse(ClientResponse clientResponse) {
    final var httpStatusCode = clientResponse.statusCode();
    return clientResponse.bodyToMono(ErrorResponse.class)
        .onErrorResume(throwable -> {
          log.warn("Exception thrown while reading HTTP body received.", throwable);
          final var emptyErrorResponse = ErrorResponse.builder()
              .build();
          return Mono.just(emptyErrorResponse);
        })
        .map(errorResponse -> {
          final var stringBuilder = new StringBuilder("Address retrieval from randomuser.me returned status code ")
              .append(httpStatusCode.value());

          Optional.ofNullable(errorResponse)
              .map(ErrorResponse::getError)
              .ifPresent(errorMessage ->
                  stringBuilder.append(" and message \"")
                      .append(errorMessage)
                      .append("\".")
              );
          stringBuilder.append(".");

          return new IllegalStateException(stringBuilder.toString());
        });
  }

  protected abstract URI buildUri(UriBuilder uriBuilder, int amount);

  protected abstract ParameterizedTypeReference<RandomUserResponse<T>> retrieveParameterizedTypeReference();
}
