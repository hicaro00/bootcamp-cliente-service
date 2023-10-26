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

import java.math.BigDecimal;

@Service
@Log4j2
public class BankAccountServiceImp implements BankAccountService {

  @Autowired
  private WebClient webClient;

  @Override
  public Mono<BankAccountDto> createdNewAccount(Mono<BankAccountDto> bankAccountDtoMono, String customerid) {

    return bankAccountDtoMono
            .map(bankAccountDto -> {
              bankAccountDto.setId(customerid);
              bankAccountDto.setBalance(BigDecimal.ZERO);
              return bankAccountDto;
            })
            .flatMap(dto -> webClient.post()
                    .uri("http://localhost:8090/account")
                    .body(Mono.just(dto), BankAccountDto.class)
                    .retrieve()
                    .onStatus(HttpStatus::is4xxClientError, clientResponse -> Mono.error(new CustomException("Error 4xx")))
                    .onStatus(HttpStatus::is5xxServerError, clientResponse -> Mono.error(new CustomException("Error 5xx")))
                    .bodyToMono(BankAccountDto.class)
            );
  }

}
