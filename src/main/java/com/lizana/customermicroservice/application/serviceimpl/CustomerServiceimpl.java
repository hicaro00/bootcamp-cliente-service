package com.lizana.customermicroservice.application.serviceimpl;

import com.lizana.customermicroservice.application.serviceskafka.CustomerEventsService;
import com.lizana.customermicroservice.domain.apputils.CustomerUtils;
import com.lizana.customermicroservice.domain.dto.CustomerDto;
import com.lizana.customermicroservice.domain.objetos.ClientStatus;
import com.lizana.customermicroservice.infrastructure.inputport.CustomerService;
import com.lizana.customermicroservice.infrastructure.outputPort.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerServiceimpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository ;

    @Autowired
    private CustomerEventsService customerEventsService;

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
        return customerDtoMono.map(customerDto -> {
            if ("PERSONAL".equals(customerDto.getClientType())){
                customerDto.setClientStatus(new ClientStatus(true,true));
            } else if ("BUSSINES".equals(customerDto.getClientType())) {
                return customerDto;
            }else {
                throw new IllegalArgumentException("clientType must be 'PERSONAL' or 'BUSSINES'");
            }
            return customerDto;
                })
                .map(CustomerUtils::dtoToEntity)
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