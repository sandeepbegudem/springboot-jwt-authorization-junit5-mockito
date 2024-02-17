package com.sandeepbegudem.customer.payments.service.repository;

import com.sandeepbegudem.customer.payments.service.dto.CustomerPaymentsRequest;
import com.sandeepbegudem.customer.payments.service.dto.CustomerResponse;
import com.sandeepbegudem.customer.payments.service.entity.Customer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Transactional
    @Query("SELECT customer FROM Customer customer")
    List<Customer> findAllCustomers();

    @Transactional
    @Query("SElECT customer FROM Customer customer WHERE customer.id= :id")
    Customer findCustomerById(int id);


//   @Query(value = "SELECT new com.sandeepbegudem.customer.payments.service.entity.Customer" +
//           "(c.id, c.firstname, c.lastname, c.age, c.address, c.city, c.state, c.payments) FROM Customer c")
//   <CustomerResponse>
//   List<CustomerResponse> getAllCustomers();
////   CustomerResponse getACustomerWithPayments();
//
//   @Query("insert into Customer(id, firstname, lastname, age, address, city, state, payments) values(:id, :firstname, :lastname, :age, :address, :city, :state, :payments)")
//   <CustomerResponse>
//   CustomerResponse insertCustomerAndPayment(@Param("id") Integer id,
//                                             @Param("firstname") String firstname,
//                                             @Param("lastname") String lastname,
//                                             @Param("age") Integer age,
//                                             @Param("address") String address,
//                                             @Param("city") String city,
//                                             @Param("state") String state,
//                                             @Param("payments") String payments);

//   @Query("insert into Customer(customer) values(:customer)")
//   CustomerResponse insertCustomerAndPayment(@Param("customer") Customer customer);

//   CustomerResponse updateCustomerAndPayment(CustomerRequest custRequest);
//   void deleteCustomer(int id);
}
