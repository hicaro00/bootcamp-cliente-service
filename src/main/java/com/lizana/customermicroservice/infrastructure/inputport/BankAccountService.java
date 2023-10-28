package com.lizana.customermicroservice.infrastructure.inputport;

import com.lizana.customermicroservice.domain.dto.BankAccountDto;
import reactor.core.publisher.Mono;

public interface BankAccountService {

  public Mono<BankAccountDto> createdNewAccount(BankAccountDto bankAccountDto);
}
