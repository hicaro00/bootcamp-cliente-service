package com.lizana.customermicroservice.application.serviceskafka;

import com.lizana.customermicroservice.domain.dto.CustomerDto;
import com.lizana.customermicroservice.domain.objetos.Event;
import com.lizana.customermicroservice.domain.objetos.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;
@Service
public class CustomerEventsService {
    @Autowired
    private KafkaTemplate<String, Event<?>> producer;
    @Value("${topic.customer.name:customer}")
    private  String topicCustomer;

    public void publish (CustomerDto customer){
        CustomerCreatedEvent created = new CustomerCreatedEvent();
        created.setData(customer);
        created.setId(UUID.randomUUID().toString());
        created.setType(EventType.CREATED);
        created.setDate(new Date());

        this.producer.send(topicCustomer,created);
    }
}
