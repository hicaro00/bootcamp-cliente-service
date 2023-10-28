package com.lizana.customermicroservice.application.serviceimpl;

import com.lizana.customermicroservice.application.customexeption.CustomException;
import com.lizana.customermicroservice.domain.dto.BankAccountDto;
import com.lizana.customermicroservice.infrastructure.inputport.BankAccountService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Log4j2
public class BankAccountServiceImp implements BankAccountService {

  @Autowired
  private WebClient webClient;

  @Override
  public Mono<BankAccountDto> createdNewAccount(BankAccountDto bankAccountDto) {

	// Realiza la solicitud POST
	return webClient.post()
		.uri("http://localhost:8095/account")
		.body(Mono.just(bankAccountDto), BankAccountDto.class)
		.retrieve()
		.onStatus(HttpStatus::is4xxClientError, clientResponse ->
			Mono.error(new CustomException("Error 4xx")))
		.onStatus(HttpStatus::is5xxServerError, clientResponse ->
			Mono.error(new CustomException("Error 5xx")))
		.bodyToMono(BankAccountDto.class);

  }

}
