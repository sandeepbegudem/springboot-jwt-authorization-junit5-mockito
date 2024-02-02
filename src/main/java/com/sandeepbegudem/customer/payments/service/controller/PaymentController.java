package com.sandeepbegudem.customer.payments.service.controller;

import com.sandeepbegudem.customer.payments.service.dto.PaymentDto;
import com.sandeepbegudem.customer.payments.service.entity.Payment;
import com.sandeepbegudem.customer.payments.service.repository.PaymentsRepository;
import com.sandeepbegudem.customer.payments.service.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<Payment> insertPayment(@RequestBody PaymentDto dto){
        return new ResponseEntity<>(paymentService.savePayment(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PaymentDto>> getAllPayments(){
        return new ResponseEntity<>(paymentService.getAllPayments(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<PaymentDto>> paymentsById(@PathVariable int id){
        return new ResponseEntity<>(paymentService.getPaymentById(id), HttpStatus.OK);
    }
}
