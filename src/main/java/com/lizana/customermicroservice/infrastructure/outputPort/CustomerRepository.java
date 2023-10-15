package com.lizana.customermicroservice.infrastructure.outputPort;

import com.lizana.customermicroservice.domain.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends ReactiveMongoRepository<Customer,String> {
}
