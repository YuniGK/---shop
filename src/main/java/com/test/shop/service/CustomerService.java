package com.test.shop.service;

import com.test.shop.domain.CreateCustomer;
import com.test.shop.domain.Customer;
import com.test.shop.domain.dto.CustomerDto;
import com.test.shop.reopsitory.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerDto newCustomer(CreateCustomer customer){
        //dto <- entity
        Customer entity = Customer.newCustomer(customer);
        Customer saved = customerRepository.save(entity);

        return CustomerDto.builder()
                .name(saved.getName())
                .address(saved.getAddress())
                .phoneNumber(saved.getPhneNumber())
                .build();
    }
}
