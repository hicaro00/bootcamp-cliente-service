package com.lizana.customermicroservice.infrastructure.inputPort;

import com.lizana.customermicroservice.domain.dto.CustomerDto;
import com.lizana.customermicroservice.domain.entity.Customer;
import com.lizana.customermicroservice.infrastructure.outputPort.CustomerRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {

    public Flux<CustomerDto>  getCustomers ();

    public Mono<CustomerDto> getById(String id);
    public Mono<CustomerDto> saveCustomer(Mono<CustomerDto> customerDtoMono);
    public Mono<CustomerDto> updateCustomer(Mono<CustomerDto> customerDtoMono, String id);
    public Mono<Void> deleteCustomer(String id);
}
