package com.sandeepbegudem.customer.payments.service.controller;

import com.sandeepbegudem.customer.payments.service.dto.CustomerPaymentsRequest;
import com.sandeepbegudem.customer.payments.service.entity.Customer;
import com.sandeepbegudem.customer.payments.service.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<CustomerPaymentsRequest>> retrieveAllCustomers(){

        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Customer> insertCustomer(@RequestBody CustomerPaymentsRequest request){

        return new ResponseEntity<>(customerService.saveCustomer(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerPaymentsRequest> retieveCustomerById(@PathVariable Integer id){

        return new ResponseEntity<>(customerService.customerById(id), HttpStatus.OK);
    }
}
