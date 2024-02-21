package com.sandeepbegudem.customer.payments.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = "com.sandeepbegudem.customer.payments.service.entity")
public class CustomerPaymentsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerPaymentsServiceApplication.class, args);
	}

}
