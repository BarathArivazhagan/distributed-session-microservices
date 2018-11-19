package com.barath.app.controller;

import java.lang.invoke.MethodHandles;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barath.app.entity.Customer;
import com.barath.app.service.CustomerService;

@CrossOrigin("*")
@RestController
@RequestMapping("/customers")
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(value="/create",produces = MediaType.APPLICATION_JSON_UTF8_VALUE,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Customer saveCustomer(@RequestBody  Customer customer){
        if(logger.isInfoEnabled()) { logger.info("creating new customer {}",customer); }
        return this.customerService.createCustomer(customer);
    }


    @GetMapping(value="/{customerId}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Customer getCustomer(@PathVariable Long customerId){
        if(logger.isInfoEnabled()) { logger.info("finding customer with customerId {}",customerId); }
        return this.customerService.getCustomer(customerId);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Customer> getCustomers(){
        if(logger.isInfoEnabled()) { logger.info("retrieving all customers"); }
        return this.customerService.getCustomers();
    }
}
