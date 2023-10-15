package com.lizana.customermicroservice.application.ServiceImpl;

import com.lizana.customermicroservice.domain.appUtils.CustomerUtils;
import com.lizana.customermicroservice.domain.dto.CustomerDto;
import com.lizana.customermicroservice.domain.entity.Customer;
import com.lizana.customermicroservice.infrastructure.inputPort.CustomerService;
import com.lizana.customermicroservice.infrastructure.outputPort.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CostomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Flux<CustomerDto> getCustomers() {
        return customerRepository.findAll().map(CustomerUtils::entityToDto);
    }

    @Override
    public Mono<CustomerDto> getById(String id) {
        return customerRepository.findById(id).map(CustomerUtils::entityToDto);
    }

    @Override
    public Mono<CustomerDto> saveCustomer(Mono<CustomerDto> customerDtoMono) {
        return customerDtoMono.map(CustomerUtils::dtoToEntity)
                .flatMap(customerRepository::insert)
                .map(CustomerUtils::entityToDto);
    }

    @Override
    public Mono<CustomerDto> updateCustomer(Mono<CustomerDto> customerDtoMono, String id) {
        return customerRepository.findById(id)
                .flatMap(p->customerDtoMono.map(CustomerUtils::dtoToEntity)
                        .doOnNext(e->e.setId(id)))
                .flatMap(customerRepository::save)
                .map(CustomerUtils::entityToDto);
    }

    @Override
    public Mono<Void> deleteCustomer(String id) {
        return customerRepository.deleteById(id);
    }


}