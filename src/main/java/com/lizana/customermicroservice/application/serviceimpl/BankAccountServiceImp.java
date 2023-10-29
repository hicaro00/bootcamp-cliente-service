package com.lizana.customermicroservice.application.serviceimpl;

import com.lizana.customermicroservice.application.customexeption.CustomException;
import com.lizana.customermicroservice.domain.dto.BankAccountDto;
import com.lizana.customermicroservice.infrastructure.inputport.BankAccountService;
import com.lizana.customermicroservice.infrastructure.inputport.CustomerService;
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
  @Autowired
  CustomerService customerService;

  @Override
  public Mono<BankAccountDto> createdNewAccount(BankAccountDto bankAccountDto) {

	// Realiza la solicitud POST
	return webClient.post()
		.uri("http://bankaccount-service/account/associateaccount")
		.body(Mono.just(bankAccountDto), BankAccountDto.class)
		.retrieve()
		.onStatus(HttpStatus::is4xxClientError, clientResponse ->
			Mono.error(new CustomException("Error 4xx")))
		.onStatus(HttpStatus::is5xxServerError, clientResponse ->
			Mono.error(new CustomException("Error 5xx")))
		.bodyToMono(BankAccountDto.class);

  }
  @Override
  public Mono<BankAccountDto> createdAdditionalAccount(BankAccountDto bankAccountDto) {

	String idCustomer = bankAccountDto.getCustomerId();

	return customerService.getById(idCustomer)
		.flatMap(customerDto -> {
		  if ("PERSONAL".equals(customerDto.getClientType())) {
			// El cliente es de tipo PERSONAL
			long count = customerDto.getBankAccounts().stream()
				.filter(account -> account.getAccountType().equals(bankAccountDto.getAccountType()))
				.count();

			if (count >= 1) {
			  // El cliente ya tiene una cuenta de este tipo, lanza una excepción personalizada
			  return Mono.error(new CustomException("No se puede crear una cuenta adicional de este tipo."));
			} else {
			  // El cliente puede crear la cuenta, realiza la creación aquí
			  return createdNewAccount(bankAccountDto);
			}
		  } else if ("BUSINESS".equals(customerDto.getClientType()) && "CURRENTACCOUNT".equals(bankAccountDto.getAccountType())) {
			// El cliente es de tipo BUSINESS y se intenta crear una cuenta corriente, permite la creación
			return createdNewAccount(bankAccountDto);
		  } else {
			// Restricción no cumplida, lanza una excepción
			return Mono.error(new CustomException("solo se le permite ceuntas CURRENTACCOUNT"));
		  }
		});
  }

}
