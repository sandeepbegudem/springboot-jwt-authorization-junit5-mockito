package com.sandeepbegudem.customer.payments.service.repository;

import com.sandeepbegudem.customer.payments.service.dto.PaymentDto;
import com.sandeepbegudem.customer.payments.service.entity.Payment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentsRepository extends JpaRepository<Payment, Integer> {

    // @Transactional
    // @Query(value = "SELECT DISTINCT p.paymentId, p.paymentAmount, p.paymentDate, p.paymentMode," +
    //         " p.paymentTargetEntity, p.paymentType, p.customer_payments_fk FROM Payment p JOIN p.customer_payments_fk c")
    // List<PaymentDto> paymentsList();

    @Transactional
    @Query(value = "SELECT p FROM Payment p")
    List<PaymentDto> paymentsList();

    @Transactional
    @Query(value = "select p.paymentId, p.paymentAmount, p.paymentDate, p.paymentMode, p.paymentTargetEntity, p.paymentType, p.customer_payments_fk " +
            "FROM Payment p where p.paymentId = :paymentId")
    Optional<PaymentDto> paymentDetailsById(@Param("paymentId") int paymentId);

}
