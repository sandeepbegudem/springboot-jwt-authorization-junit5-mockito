
package com.sandeepbegudem.customer.payments.service.repository;


import com.sandeepbegudem.customer.payments.service.entity.Customer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Transactional
    @Query("SELECT customer FROM Customer customer")
    List<Customer> findAllCustomers();

    @Transactional
    @Query("SELECT customer FROM Customer customer WHERE customer.id= :id")
    Customer findCustomerById(int id);

    @Transactional
   void deleteCustomerById(int id);
}

