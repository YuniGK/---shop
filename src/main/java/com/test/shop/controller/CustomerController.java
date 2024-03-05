package com.test.shop.controller;

import com.test.shop.domain.CreateCustomer;
import com.test.shop.domain.dto.CustomerDto;
import com.test.shop.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/customers")
@RequiredArgsConstructor
@Controller
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/")
    public String getRequest(){
        return "Hi getMethod";
    }

    //http://localhost:8090/customers/new?name=yuni&address=seoul&phoneNumber=1234
    @PostMapping("/new")
    public Response<CustomerDto> createNewCustomer(@RequestParam(required = false) String name,
                                                   @RequestParam(required = false) String address,
                                                   @RequestParam(required = false) String phoneNumber){

        return Response.success(customerService.newCustomer(
                CreateCustomer.builder()
                        .name(name)
                        .address(address)
                        .phneNumber(phoneNumber)
                        .build())
        );

    }
}
