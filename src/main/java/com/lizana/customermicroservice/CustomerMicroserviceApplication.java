package com.lizana.customermicroservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableAutoConfiguration
@EnableEurekaClient
@Slf4j
@Validated
public class CustomerMicroserviceApplication {
  @Bean
  public WebClient webClient() {
	return WebClient.builder().build();
  }

  /*@Bean
  public WebClient.Builder webClientBuilder() {
	return WebClient.builder();
  }*/

  public static void main(String[] args) {
	SpringApplication.run(CustomerMicroserviceApplication.class, args);
  }

}
