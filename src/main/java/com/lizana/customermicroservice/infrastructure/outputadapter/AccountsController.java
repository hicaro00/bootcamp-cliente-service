package com.lizana.customermicroservice.infrastructure.outputadapter;

import com.lizana.customermicroservice.domain.dto.CustomerDto;
import com.lizana.customermicroservice.domain.dto.Signatory;
import com.lizana.customermicroservice.infrastructure.inputport.BankAccountService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@RestController
@Log4j2
@RequestMapping("/accountbank")
public class AccountsController {

  @Autowired
  BankAccountService bankAccountService;

  @PutMapping("/newsignatory/{acccountId}")
  @ResponseBody
 public Mono<Signatory> addSignatories(@RequestBody Signatory signatory, @PathVariable (name ="acccountId" ) String acccountId) {
	try {
	  return ResponseEntity.status(HttpStatus.OK)
		  .body(bankAccountService.addSignatoryToaccount(signatory, acccountId))
		  .getBody();
	} catch (Exception e) {
	  throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error en el servicio", e);
	}
  }






}
