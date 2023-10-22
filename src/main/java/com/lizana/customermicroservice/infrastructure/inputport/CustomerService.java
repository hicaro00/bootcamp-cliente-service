package com.lizana.customermicroservice.infrastructure.inputport;

import com.lizana.customermicroservice.domain.dto.CustomerDto;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
public interface CustomerService {

    public Flux<CustomerDto>  getCustomers ();
    public Mono<CustomerDto> getById(String id);
    public Mono<CustomerDto> saveCustomer(Mono<CustomerDto> customerDtoMono);
    public Mono<CustomerDto> updateCustomer(Mono<CustomerDto> customerDtoMono, String id);
    public Mono<Void> deleteCustomer(String id);
}
