package com.lizana.customermicroservice.infrastructure.outputadapter;


import com.lizana.customermicroservice.domain.dto.BankAccountDto;
import com.lizana.customermicroservice.domain.dto.CustomerDto;
import com.lizana.customermicroservice.infrastructure.inputport.BankAccountService;
import com.lizana.customermicroservice.infrastructure.inputport.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/customer")
public class CustomerController {
  @Autowired
  CustomerService customerService;

  @Autowired
  BankAccountService bankAccountService;

  @GetMapping
  @ResponseBody
  public Flux<CustomerDto> getCustomers() {
	Flux<CustomerDto> customers = customerService.getCustomers();
	return ResponseEntity.status(HttpStatus.OK).body(customers).getBody();
  }

  @GetMapping("/{id}")
  @ResponseBody
  public Mono<CustomerDto> getCustomer(@PathVariable String id) {
	Mono<CustomerDto> getOneCustomer = customerService.getById(id);
	return ResponseEntity.status(HttpStatus.GONE).body(getOneCustomer).getBody();
  }

  @PostMapping
  public Mono<ResponseEntity<CustomerDto>> createdCustomer(
	  @RequestBody Mono<CustomerDto> customerDtoMono) {

	return customerService.saveCustomer(customerDtoMono).map(ResponseEntity::ok);
  }



  @DeleteMapping("/delete/{id}")    //borrar cliente
  public Mono<Void> deleteCustomer(@PathVariable(name = "id") String id) {
	return customerService.deleteCustomer(id);
  }

  @PostMapping("/newaccount")  // crea y asocia una nueva cuenta de banco
  @CrossOrigin
  public Mono<BankAccountDto> nueaccount(
	  @RequestBody BankAccountDto bankAccountDto) {
	return bankAccountService.createdAdditionalAccount(bankAccountDto);

  }


  @PutMapping("/updatecustomer/{id}")  //actualiza solo el requerido
  @CrossOrigin
  public Mono<ResponseEntity<CustomerDto>> addBankAccount(
	  @RequestBody Mono<CustomerDto> customerDtoMono, @PathVariable String id) {
	return customerDtoMono.flatMap(customerDto -> {
	  Mono<CustomerDto> existCustomerDtoMOno = customerService.getById(id);
	  return existCustomerDtoMOno.map(existCustomerDto -> {
			existCustomerDto.getBankAccounts().addAll(customerDto.getBankAccounts());
			return existCustomerDto;
		  }).flatMap(updatedCustomerDto -> {
			return customerService.updateCustomer(Mono.just(updatedCustomerDto), id);
		  }).map(update -> ResponseEntity.status(HttpStatus.OK).body(update))
		  .defaultIfEmpty(ResponseEntity.notFound().build());
	});
  }

}
