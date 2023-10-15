package com.lizana.customermicroservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@Slf4j
public class CustomerMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerMicroserviceApplication.class, args);
	}

}
