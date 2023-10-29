package com.lizana.customermicroservice.infrastructure.outputadapter;

import com.lizana.customermicroservice.domain.dto.CustomerDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@Log4j2
@RequestMapping("/credits")
public class CreditsController {

  @PostMapping
  @ResponseBody
  public Mono<CustomerDto> createCredit(CustomerDto customerDto) {
	return null;
  }

}
