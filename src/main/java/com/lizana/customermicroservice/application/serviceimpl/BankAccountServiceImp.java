package com.lizana.customermicroservice.application.serviceimpl;

import com.lizana.customermicroservice.domain.dto.BankAccountDto;
import com.lizana.customermicroservice.infrastructure.inputport.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
@Service
public class BankAccountServiceImp implements BankAccountService {
    @Autowired
    private WebClient webClient;
    @Override
    public Mono<BankAccountDto> createdNewAccount(Mono<BankAccountDto> bankAccountDtoMono) {

        return bankAccountDtoMono.flatMap(dto -> webClient.post()
                .uri("http://localhost:8090/account")
                .body(Mono.just(dto), BankAccountDto.class)
                .retrieve()
                .bodyToMono(BankAccountDto.class));
    }
}
