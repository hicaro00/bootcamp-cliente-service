package com.lizana.customermicroservice.infrastructure.outputAdapter;

import com.lizana.customermicroservice.application.serviceImpl.CustomerEventsService;
import com.lizana.customermicroservice.domain.dto.CustomerDto;
import com.lizana.customermicroservice.infrastructure.inputPort.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Slf4j
@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    CustomerEventsService customerEventsService;

    @GetMapping
    @ResponseBody
    public Flux<CustomerDto> getCustomers(){
        Flux<CustomerDto>customers = customerService.getCustomers();
        return ResponseEntity.status(HttpStatus.OK).body(customers).getBody();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Mono<CustomerDto> getCustomer(@PathVariable String id){
        Mono<CustomerDto> getOneCustomer =customerService.getById(id);
        return ResponseEntity.status(HttpStatus.GONE).body(getOneCustomer).getBody();
    }

    @PostMapping
    public Mono<ResponseEntity<CustomerDto>> postCustomer(@RequestBody Mono<CustomerDto> customerDtoMono){

        return customerService.saveCustomer(customerDtoMono).map(ResponseEntity::ok);
    }

    @PutMapping("/update/{id}")
    @ResponseBody
    public Mono<CustomerDto> putCustomer (@RequestBody Mono<CustomerDto> customerDtoMono , String id){
        Mono<CustomerDto> putCustomer = customerService.updateCustomer(customerDtoMono, id);
        return ResponseEntity.status(HttpStatus.OK).body(putCustomer).getBody();
    }

    @DeleteMapping("/delete/{id}")
    public Mono<Void> deleteCustomer (@PathVariable String id){
        return customerService.deleteCustomer(id);
    }



}
