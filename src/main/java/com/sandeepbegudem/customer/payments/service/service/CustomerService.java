package com.sandeepbegudem.customer.payments.service.service;
import com.sandeepbegudem.customer.payments.service.dto.CustomerPaymentsRequest;
import com.sandeepbegudem.customer.payments.service.dto.CustomerResponse;
import com.sandeepbegudem.customer.payments.service.entity.Customer;
import com.sandeepbegudem.customer.payments.service.mapper.CustomerMapper;
import com.sandeepbegudem.customer.payments.service.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerMapper mapper;

    // retrieve all customers
//    public List<CustomerPaymentsRequest> getAllCustomers(){
//        List<CustomerPaymentsRequest> customerPaymentsRequests = customerRepository
//                .findAll()
//                .parallelStream()
//                .map(m -> mapper.entityToDto(m))
//                .collect(Collectors.toList());
//        return customerPaymentsRequests;
//    }

    // save a customer
    public Customer saveCustomer(CustomerPaymentsRequest customerPaymentsRequest) {
        Customer customerList = mapper.dtoToEntity(customerPaymentsRequest);
        Customer savedCustomer = customerRepository.save(customerList);
        return savedCustomer;
    }

    // retrieve a customer by id
//    public CustomerPaymentsRequest customerById(Integer id){
//        Customer customer = customerRepository.findById(id).orElse(null);
//        CustomerPaymentsRequest customerPaymentsRequest = mapper.entityToDto(customer);
//        return customerPaymentsRequest;
//    }

    public CustomerPaymentsRequest customerById(int id){
        Customer customer = customerRepository.findCustomerById(id);
        CustomerPaymentsRequest customerPaymentsRequest = mapper.entityToDto(customer);
        return customerPaymentsRequest;
    }

    // update customer
    public Customer updateCustomer(CustomerPaymentsRequest customerPaymentsRequest, Integer id) {
        Customer customer = mapper.dtoToEntity(customerPaymentsRequest);
        Customer retrievedCustomer = customerRepository.findById(customer.getId()).orElse(null);
        Customer savedCustomer = customerRepository.save(retrievedCustomer);
        return savedCustomer;
    }

    // delete customer
    public void deleteCustomerById(int id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer.getId() == null) {
            throw new RuntimeException("resource: not found" + id);
        }
        else
            customerRepository.deleteById(customer.getId());
    }

    public List<CustomerPaymentsRequest> getAllCustomersUsingCustomJPQL() {

       return customerRepository.findAllCustomers()
               .stream()
               .map(customer -> mapper.entityToDto(customer))
               .collect(Collectors.toList());
    }

}
