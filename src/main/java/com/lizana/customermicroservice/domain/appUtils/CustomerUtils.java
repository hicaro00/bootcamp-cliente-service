package com.lizana.customermicroservice.domain.appUtils;

import com.lizana.customermicroservice.domain.dto.CustomerDto;
import com.lizana.customermicroservice.domain.entity.Customer;
import org.springframework.beans.BeanUtils;

public class CustomerUtils {
    public static CustomerDto entityToDto(Customer customer){
        CustomerDto customerDto = new CustomerDto();
        BeanUtils.copyProperties(customer ,customerDto);
        return customerDto;
    }

    public static Customer dtoToEntity(CustomerDto customerDto){
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDto ,customer);
        return customer;
    }
}
