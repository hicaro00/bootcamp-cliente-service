package com.lizana.customermicroservice.application.serviceimpl;

import com.lizana.customermicroservice.application.customexeption.CustomException;
import com.lizana.customermicroservice.domain.dto.BankAccountDto;
import com.lizana.customermicroservice.domain.dto.CustomerDto;
import com.lizana.customermicroservice.domain.dto.Signatory;
import com.lizana.customermicroservice.infrastructure.inputport.BankAccountService;
import com.lizana.customermicroservice.infrastructure.inputport.CustomerService;
import com.lizana.customermicroservice.infrastructure.inputport.WebClientServicePost;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Log4j2
public class BankAccountServiceImp implements BankAccountService {

  private static final String ACCOUNT_ADDSIGNATORY_ACCCOUNT_ID = "http://localhost:8090/account/addsignatory/{accountId}";

  @Autowired
   WebClient webClient;
  @Autowired
  CustomerService customerService;
  @Autowired
  WebClientServicePost webClientService;




  @Override
  public Mono<BankAccountDto> createdNewAccount(BankAccountDto bankAccountDto) {

	// Realiza la solicitud POST
	return webClient.post()
		.uri("http://localhost:8090/account/associateaccount")
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
			  // El cliente ya tiene una cuenta de este tipo,
			  return Mono.error(
				  new CustomException("No se puede crear una cuenta adicional de este tipo."));
			} else {
			  // El cliente puede crear la cuenta, realiza la creación aquí
			  return createdNewAccount(bankAccountDto);
			}
		  } else if ("BUSINESS".equals(customerDto.getClientType()) &&
			  "CURRENTACCOUNT".equals(bankAccountDto.getAccountType())) {
			// El cliente es de tipo BUSINESS y se intenta crear una cuenta corriente, permite la creación
			return createdNewAccount(bankAccountDto);
		  } else {
			// Restricción no cumplida, lanza una excepción
			return Mono.error(new CustomException("solo se le permite ceuntas CURRENTACCOUNT"));
		  }
		});
  }

  @Override
  public Mono<Signatory> addSignatoryToaccount(Signatory signatory, String accountId) {
	String customerId = signatory.getCustomerId();
//llega el firmante en el signatoty  y id del cliente Personal o business
	return customerService.getById(customerId) //obtengo el dato del clente si es busiines si agrega el signatori
		.flatMap(customerDto -> {

		  if ("BUSINESS".equals(customerDto.getClientType())) { // devo enviart el signatory y el id de la cuenta a la que se quiere agregar que deve ser CURRENT ACCOUNT
			return webClientService.metodoPut(signatory, ACCOUNT_ADDSIGNATORY_ACCCOUNT_ID, Signatory.class,accountId)
				.flatMap(response -> {
				  // Procesar la respuesta si es necesario
				  return Mono.just(response);
				});
		  } else if ("PERSONAL".equals(customerDto.getClientType())) {
			return Mono.error(new CustomException("El cliente personal es el único firmante"));
		  } else {
			return Mono.error(new CustomException("Solo los clientes BUSINESS pueden tener más de un firmante"));
		  }
		});


  }

}
