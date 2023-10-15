package com.lizana.customermicroservice.infrastructure.outputAdapter;

import com.lizana.customermicroservice.domain.dto.CustomerDto;
import com.lizana.customermicroservice.infrastructure.inputPort.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Slf4j
@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public Flux<CustomerDto> getCustomers(){
        return customerService.getCustomers();
    }

    @GetMapping("/{id}")
    public Mono<CustomerDto> getCustomer(@PathVariable String id){
        return customerService.getById(id);
    }

    @PostMapping
    public Mono<CustomerDto> postCustomer(@RequestBody Mono<CustomerDto> customerDtoMono){
        return customerService.saveCustomer(customerDtoMono);
    }
    @PutMapping("/update/{id}")
    public Mono<CustomerDto> putCustomer (@RequestBody Mono<CustomerDto> customerDtoMono , String id){
        return customerService.updateCustomer(customerDtoMono, id);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<Void> deleteCustomer (@PathVariable String id){
        return customerService.deleteCustomer(id);
    }



}
