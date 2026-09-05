package com.finflow.gateway.payment.dto;

import com.finflow.gateway.payment.enums.PaymentMethod;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PaymentCreateRequest {

    @NotNull
    private Long merchantId;

    @NotNull
    @DecimalMin(value = "0.01")
    private BigDecimal amount;

    @NotNull
    @Size(min = 3, max = 3)
    private String currency;

    @NotNull
    private PaymentMethod paymentMethod;
}