package com.lizana.customermicroservice.application.serviceimpl;

import com.lizana.customermicroservice.application.customexeption.CustomException;
import com.lizana.customermicroservice.infrastructure.inputport.WebClientServicePost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class WebClientServicePostImpl implements WebClientServicePost {
  @Autowired
  WebClient webClient;


  @Override
  public <T> Mono<T> webClietnePost(T dto, String endpoint, Class<T> responseType) {

	return webClient.post()
		.uri(endpoint)
		.body(Mono.just(dto), dto.getClass())
		.retrieve()
		.onStatus(HttpStatus::is4xxClientError, clientResponse ->
			Mono.error(new CustomException("Error 4xx")))
		.onStatus(HttpStatus::is5xxServerError, clientResponse ->
			Mono.error(new CustomException("Error 5xx")))
		.bodyToMono(responseType);
  }


  public <T> Mono<T> metodoPut(T dto, String endpoint, Class<T> responseType, String accountId) {
	return webClient.put()
		.uri(endpoint, accountId)
		.bodyValue(dto)
		.retrieve()
		.bodyToMono(responseType);
  }

}
