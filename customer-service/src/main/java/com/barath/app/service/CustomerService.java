package com.barath.app.service;


import java.lang.invoke.MethodHandles;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.barath.app.entity.Customer;
import com.barath.app.exception.CustomerNotFoundException;
import com.barath.app.repository.CustomerRepository;

@Service
public class CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(Customer customer){

        return this.customerRepository.save(customer);
    }

    public Customer getCustomer(Long customerId){

        Optional<Customer> customerOptional= this.customerRepository.findById(customerId);
        if(customerOptional.isPresent()){
            return customerOptional.get();
        }
        throw new CustomerNotFoundException("Customer with customer id "+customerId+" not found");
    }

    public List<Customer> getCustomers(){

        List<Customer> customers = this.customerRepository.findAll();
        if(logger.isInfoEnabled()){
            customers.forEach(customer -> logger.info(customer.toString()));
        }
        return customers;
    }



    @PostConstruct
    public void init(){

        Customer customer1 = new Customer(1L,"BARATH");
        Customer customer2 = new Customer(2L,"SAI");
        Customer customer3 = new Customer(3L,"SACHIN");
        Customer customer4 = new Customer(4L,"KOHLI");
        Customer customer5 = new Customer(5L,"DHONI");
        Customer customer6 = new Customer(6L,"KHAN");
        Customer customer7 = new Customer(7L,"ABDUL");
      
        Arrays.asList(customer1,customer2,customer3,customer4,customer5,customer6,customer7)
                .forEach(this::createCustomer);

    }
}
