package com.sandeepbegudem.customer.payments.service.service;

import com.sandeepbegudem.customer.payments.service.dto.CustomerPaymentsRequest;
import com.sandeepbegudem.customer.payments.service.entity.Customer;
import com.sandeepbegudem.customer.payments.service.mapper.CustomerMapper;
import com.sandeepbegudem.customer.payments.service.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;
    @Mock
    private CustomerMapper customerMapper;
    @Mock
    private CustomerRepository customerRepository;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_save_a_customer(){
    // Given

        CustomerPaymentsRequest request = new CustomerPaymentsRequest();
        request.setId(1);
        request.setFirstname("john");
        request.setLastname("doe");
        request.setAge(21);
        request.setAddress("123 main st");
        request.setCity("sanjose");
        request.setState("california");
        request.setPayments(null);

        Customer customer = new Customer();
        customer.setId(1);
        customer.setFirstname("john");
        customer.setLastname("doe");
        customer.setAge(21);
        customer.setAddress("123 main st");
        customer.setCity("sanjose");
        customer.setState("california");
        customer.setPayments(null);

        when(customerMapper.dtoToEntity(request)).thenReturn(customer);
        when(customerRepository.save(customer)).thenReturn(customer);

        // When
        Customer savedCustomer = customerService.saveCustomer(request);
        CustomerPaymentsRequest customerRequest = customerMapper.entityToDto(savedCustomer);

        System.out.println(savedCustomer.toString());
        Customer customerResponse = customerRepository.save(savedCustomer);

        // Then
        assertEquals(request.getId(), customerResponse.getId());
        assertEquals(request.getFirstname(), customerResponse.getFirstname());
        assertEquals(request.getLastname(), customerResponse.getLastname());
        assertEquals(request.getAge(), customerResponse.getAge());
        assertEquals(request.getAddress(), customerResponse.getAddress());
        assertEquals(request.getCity(), customerResponse.getCity());
        assertEquals(request.getState(), customerResponse.getState());
       // assertNull((Object) request.getPayments(), (Supplier<String>) customerResponse.getPayments());

    }

    @Test
    public void should_get_all_customers(){

        // Given
        List<Customer> cr = new ArrayList<>();
        cr.add(new Customer(
                1,
                "john",
                "doe",
                21,
                "123 main st",
                "denver",
                "colorado",
                null));

       CustomerPaymentsRequest crReq = new CustomerPaymentsRequest(
               1,
                "john",
                "doe",
                21,
                "123 main st",
                "denver",
                "colorado",
                null);

        when(customerRepository.findAll()).thenReturn((List<Customer>) cr);
        when(customerMapper.entityToDto(any(Customer.class)))
                .thenReturn(new CustomerPaymentsRequest(1,
                "john",
                "smith",
                21,
                "123 main st",
                "denver",
                "california",
                null));

        // When
        customerService.getAllCustomers();

        // Then
        assertEquals(cr.get(0).getId(), crReq.getId());
        assertEquals(cr.get(0).getFirstname(), crReq.getFirstname());
        assertEquals(cr.get(0).getLastname(), crReq.getLastname());
        assertEquals(cr.get(0).getCity(), crReq.getCity());
        assertEquals(cr.get(0).getState(), crReq.getState());


    }
    @Test
    public void should_retrieve_a_customer_by_Id(){

        Customer customer = new Customer();
        customer.setId(1);
        customer.setFirstname("john");
        customer.setLastname("doe");
        customer.setAge(21);
        customer.setAddress("123 main st");
        customer.setCity("denver");
        customer.setState("colorado");
        customer.setPayments(null);

        // Mock the calls
        when(customerRepository.findById(customer.getId()))
                .thenReturn(Optional.of(customer));
        when(customerMapper.entityToDto(any(Customer.class)))
                .thenReturn(new CustomerPaymentsRequest(1,
                        "john",
                        "doe",
                        21,
                        "123 main st",
                        "denver",
                        "colorado",
                        null));

        CustomerPaymentsRequest custResponse = customerService.customerById(customer.getId());

        assertEquals(customer.getId(), custResponse.getId());
        assertEquals(customer.getFirstname(), custResponse.getFirstname());
        assertEquals(customer.getLastname(), custResponse.getLastname());
        assertEquals(customer.getAge(), custResponse.getAge());
        assertEquals(customer.getAddress(), custResponse.getAddress());
        assertEquals(customer.getCity(), custResponse.getCity());
        assertEquals(customer.getState(), custResponse.getState());
        Assertions.assertNull((Object) customer.getPayments(), (Supplier<String>) custResponse.getPayments());
       // Assertions.assertNotNull((Object) customer.getPayments(), (Supplier<String>) custResponse.getPayments()); Fails as expected

        verify(customerRepository, times(1)).findById(customer.getId());
        verify(customerMapper, times(1)).entityToDto(customer);
        verify(customerMapper, atLeastOnce()).entityToDto(customer);
    }

    // Todo: write testcases for


}
