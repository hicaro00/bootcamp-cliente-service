package com.lizana.customermicroservice.infrastructure.inputport;

import com.lizana.customermicroservice.domain.dto.BankAccountDto;
import com.lizana.customermicroservice.domain.dto.CustomerDto;
import com.lizana.customermicroservice.domain.dto.Signatory;
import reactor.core.publisher.Mono;

public interface BankAccountService {

  public Mono<BankAccountDto> createdNewAccount(BankAccountDto bankAccountDto);

  Mono<BankAccountDto> createdAdditionalAccount(BankAccountDto bankAccountDto);

  Mono<Signatory> addSignatoryToaccount(Signatory signatory, String acccountId);

}
