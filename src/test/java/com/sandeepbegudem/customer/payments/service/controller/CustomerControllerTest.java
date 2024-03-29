
package com.sandeepbegudem.customer.payments.service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sandeepbegudem.customer.payments.service.dto.CustomerPaymentsRequest;
import com.sandeepbegudem.customer.payments.service.dto.CustomerResponse;
import com.sandeepbegudem.customer.payments.service.entity.Customer;
import com.sandeepbegudem.customer.payments.service.service.CustomerService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(controllers = CustomerController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void insertCustomer_ShouldReturn_Status_201_Created() throws Exception {

        CustomerPaymentsRequest dto = new CustomerPaymentsRequest(
                1,
                "john",
                "doe",
                21,
                "123 main st",
                "denver",
                "colorado",
                null);

        CustomerResponse customerResponse = new CustomerResponse(1,
                "john",
                "doe",
                21,
                "123 main st",
                "denver",
                "colorado", null);

        BDDMockito.given(customerService.saveCustomer(
                ArgumentMatchers
                        .any()))
                .willReturn(customerResponse)
                .willAnswer(InvocationOnMock::getArguments);
        ResultActions result = mockMvc.perform(post("/api/v1/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)));

        result.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is(dto.getId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstname", CoreMatchers.is(dto.getFirstname())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastname", CoreMatchers.is(dto.getLastname())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age", CoreMatchers.is(dto.getAge())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address", CoreMatchers.is(dto.getAddress())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.city", CoreMatchers.is(dto.getCity())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.state", CoreMatchers.is(dto.getState())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.payments", CoreMatchers.is(dto.getPayments())))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void should_return_Customers_List() throws Exception {

        List<CustomerResponse> dto = Arrays.asList(new CustomerResponse(
                1,
                "john",
                "doe",
                21,
                "123 main st",
                "denver",
                "colorado",
                null),
                new CustomerResponse(
                        21,
                        "john",
                        "cena",
                        22,
                        "321 main st",
                        "seattle",
                        "washington",
                        null));

        Customer customer = new Customer(1,
                "john",
                "doe",
                21,
                "123 main st",
                "denver",
                "colorado", null);


        when(customerService.getAllCustomers()).thenReturn(dto);
        ResultActions result = mockMvc.perform(get("/api/v1/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customer)));

        result.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void should_retrieve_customerById() throws Exception {

        CustomerResponse response = new CustomerResponse(
                21,
                "john",
                "doe",
                21,
                "123 main st",
                "denver",
                "colorado",
                null);

        when(customerService.customerById(response.getId())).thenReturn(response);
                ResultActions result = mockMvc.perform(get("/api/v1/customers/21")
                .contentType(MediaType.APPLICATION_JSON)
                                .param("id", String.valueOf(21))
               .content(objectMapper.writeValueAsString(response)));

        result.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstname", CoreMatchers.is(response.getFirstname())))
                .andDo(MockMvcResultHandlers.print());
    }
}

