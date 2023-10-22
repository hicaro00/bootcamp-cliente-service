package com.lizana.customermicroservice.infrastructure.inputport;

import com.lizana.customermicroservice.domain.dto.BankAccountDto;
import org.apache.kafka.common.protocol.types.Field;
import reactor.core.publisher.Mono;

public interface BankAccountService {

    public Mono<BankAccountDto> createdNewAccount(Mono<BankAccountDto> bankAccountDtoMono, String customerid);
}
