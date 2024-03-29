package com.sandeepbegudem.customer.payments.service.mapper;

import com.sandeepbegudem.customer.payments.service.dto.CustomerPaymentsRequest;
import com.sandeepbegudem.customer.payments.service.dto.CustomerResponse;
import com.sandeepbegudem.customer.payments.service.entity.Customer;
import lombok.*;
import org.springframework.stereotype.Component;


@AllArgsConstructor
@Setter
@Getter
@Component
public class CustomerMapper {
    public CustomerPaymentsRequest entityToDto(Customer customer){
        CustomerPaymentsRequest dto = new CustomerPaymentsRequest();
        dto.setId(customer.getId());
        dto.setFirstname(customer.getFirstname());
        dto.setLastname(customer.getLastname());
        dto.setAge(customer.getAge());
        dto.setAddress(customer.getAddress());
        dto.setCity(customer.getCity());
        dto.setState(customer.getState());
        dto.setPayments(customer.getPayments());
        return dto;
    }

    public Customer dtoToEntity(CustomerPaymentsRequest customerPaymentsRequest){
        Customer customer = new Customer();
        customer.setId(customerPaymentsRequest.getId());
        customer.setFirstname(customerPaymentsRequest.getFirstname());
        customer.setLastname(customerPaymentsRequest.getLastname());
        customer.setAge(customerPaymentsRequest.getAge());
        customer.setAddress(customerPaymentsRequest.getAddress());
        customer.setCity(customerPaymentsRequest.getCity());
        customer.setState(customerPaymentsRequest.getState());
        customer.setPayments(customerPaymentsRequest.getPayments());
        return customer;
    }

    public CustomerResponse dtoToEntityResponse(CustomerPaymentsRequest customerPaymentsRequest){
        CustomerResponse customer = new CustomerResponse();
        customer.setId(customerPaymentsRequest.getId());
        customer.setFirstname(customerPaymentsRequest.getFirstname());
        customer.setLastname(customerPaymentsRequest.getLastname());
        customer.setAge(customerPaymentsRequest.getAge());
        customer.setAddress(customerPaymentsRequest.getAddress());
        customer.setCity(customerPaymentsRequest.getCity());
        customer.setState(customerPaymentsRequest.getState());
        customer.setPayments(customerPaymentsRequest.getPayments());
        return customer;
    }

    public CustomerResponse dtoToResponse(CustomerPaymentsRequest request){

        return sendResponse(request);
    }

    private CustomerResponse sendResponse(CustomerPaymentsRequest customerResponse){

        Customer customer = new Customer();
        customer.setId(customerResponse.getId());
        customer.setFirstname(customerResponse.getFirstname());
        customer.setLastname(customerResponse.getLastname());
        customer.setAge(customerResponse.getAge());
        customer.setAddress(customerResponse.getAddress());
        customer.setCity(customerResponse.getCity());
        customer.setState(customerResponse.getState());
        customer.setPayments(customerResponse.getPayments());


        return new CustomerResponse(
                customerResponse.getId(),
                customerResponse.getFirstname(),
                customerResponse.getLastname(),
                customerResponse.getAge(),
                customerResponse.getAddress(),
                customerResponse.getCity(),
                customerResponse.getState(),
                customerResponse.getPayments());
    }

    public CustomerResponse entityToResponse(Customer customer){

        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setId(customer.getId());
        customerResponse.setFirstname(customerResponse.getFirstname());
        customerResponse.setLastname(customerResponse.getLastname());
        customerResponse.setAge(customerResponse.getAge());
        customerResponse.setAddress(customerResponse.getAddress());
        customerResponse.setCity(customerResponse.getCity());
        customerResponse.setState(customerResponse.getState());
        customerResponse.setPayments(customer.getPayments());

        return new CustomerResponse(
                customerResponse.getId(),
                customerResponse.getFirstname(),
                customerResponse.getLastname(),
                customerResponse.getAge(),
                customerResponse.getAddress(),
                customerResponse.getCity(),
                customerResponse.getState(),
                customerResponse.getPayments());
    }

    public static CustomerResponse apply(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getFirstname(),
                customer.getLastname(),
                customer.getAge(),
                customer.getAddress(),
                customer.getCity(),
                customer.getState(),
                customer.getPayments()
        );
    }

}