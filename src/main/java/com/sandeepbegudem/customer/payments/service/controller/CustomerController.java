package com.sandeepbegudem.customer.payments.service.controller;

import com.sandeepbegudem.customer.payments.service.dto.CustomerPaymentsRequest;
import com.sandeepbegudem.customer.payments.service.dto.CustomerResponse;
import com.sandeepbegudem.customer.payments.service.entity.Customer;
import com.sandeepbegudem.customer.payments.service.service.CustomerService;
import com.sandeepbegudem.customer.payments.service.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private CustomerService customerService;

    private JwtService jwtService;

    private AuthenticationManager authenticationManager;

    @Autowired
    public CustomerController(CustomerService customerService, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.customerService = customerService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<CustomerResponse>> getAllCustomers(){

        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
    }

//    @GetMapping
//    @PreAuthorize("hasAuthority('ADMIN')")
//    public ResponseEntity<List<CustomerPaymentsRequest>> retrieveAllCustomers(){
//
//        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
//    }

    @PostMapping
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<CustomerResponse> insertCustomer(@RequestBody CustomerPaymentsRequest request){

        return new ResponseEntity<>(customerService.saveCustomer(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<CustomerResponse> retrieveCustomerById(@PathVariable int id){

        return new ResponseEntity<>(customerService.customerById(id), HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteCustomerById(@PathVariable Integer id) {
        if (id == null) {
            throw new RuntimeException("id: " + " not found");
        }
        else
            customerService.deleteCustomerById(id);
    }
}
