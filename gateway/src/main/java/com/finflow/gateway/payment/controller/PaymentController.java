package com.finflow.gateway.payment.controller;

import com.finflow.gateway.payment.dto.PaymentCreateRequest;
import com.finflow.gateway.payment.dto.PaymentResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    @PostMapping
    public ResponseEntity<PaymentResponse> createPayment(
            @RequestHeader("Idempotency-Key")
            @jakarta.validation.constraints.NotBlank
            @jakarta.validation.constraints.Size(max = 100)
            String idempotencyKey,

            @Valid @RequestBody
            PaymentCreateRequest request
    ) {

        return ResponseEntity.ok().build();
    }
}