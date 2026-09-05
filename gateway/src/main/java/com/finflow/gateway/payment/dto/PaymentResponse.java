package com.finflow.gateway.payment.dto;


import com.finflow.gateway.payment.enums.PaymentMethod;
import com.finflow.gateway.payment.enums.PaymentStatus;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
public class PaymentResponse {

    private String paymentReference;

    private Long merchantId;

    private BigDecimal amount;

    private String currency;

    private PaymentMethod paymentMethod;

    private PaymentStatus status;

    private String providerReference;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}