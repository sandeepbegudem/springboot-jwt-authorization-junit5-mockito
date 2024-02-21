package com.sandeepbegudem.customer.payments.service.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class Payment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer paymentId;

    @Enumerated(EnumType.ORDINAL)
    @NotNull(message = "payment mode can't be null")
    private PaymentMode paymentMode;

    @NotNull(message = "payment target entity can't be null")
    private String paymentTargetEntity;

    @Min(100)
    @Max(2500)
    @NotNull(message = "payment amount can't be null")
    private BigDecimal paymentAmount;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "hh:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "hh:mm:ss")
    private Date paymentDate;

    @NotNull(message = "payment type mode can't be null")
    @Enumerated(EnumType.ORDINAL)
    private PaymentType paymentType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @NotNull(message = "payment id can't be null")
    private Customer customer_payments_fk;

    public Payment(Integer paymentId, PaymentMode paymentMode, String paymentTargetEntity, BigDecimal paymentAmount, Date paymentDate, PaymentType paymentType, Customer customer_payments_fk) {
        this.paymentId = paymentId;
        this.paymentMode = paymentMode;
        this.paymentTargetEntity = paymentTargetEntity;
        this.paymentAmount = paymentAmount;
        this.paymentDate = paymentDate;
        this.paymentType = paymentType;
        this.customer_payments_fk = customer_payments_fk;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = new Date();
    }
}
