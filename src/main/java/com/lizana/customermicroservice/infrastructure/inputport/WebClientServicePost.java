package com.lizana.customermicroservice.infrastructure.inputport;

import reactor.core.publisher.Mono;

public interface WebClientServicePost {

  <T> Mono<T> webClietnePost(T dto, String endpoint, Class<T> responseType);

  <T> Mono<T> metodoPut(T dto, String endpoint, Class<T> responseType, String accountId);
}
