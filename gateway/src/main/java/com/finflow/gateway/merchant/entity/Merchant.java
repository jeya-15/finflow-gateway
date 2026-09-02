package com.finflow.gateway.merchant.entity;

import com.finflow.gateway.merchant.enums.BusinessType;
import com.finflow.gateway.merchant.enums.MerchantStatus;
import com.finflow.gateway.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "merchants",
        indexes = {
                @Index(
                        name = "idx_merchants_status",
                        columnList = "status"
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Merchant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "user_id",
            nullable = false,
            unique = true
    )
    private User user;

    @Column(
            name = "merchant_reference",
            nullable = false,
            unique = true,
            length = 50
    )
    private String merchantReference;

    @Column(
            name = "business_name",
            nullable = false,
            length = 150
    )
    private String businessName;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "business_type",
            nullable = false,
            length = 50
    )
    private BusinessType businessType;

    @Enumerated(EnumType.STRING)
    @Column(
            nullable = false,
            length = 20
    )
    @Builder.Default
    private MerchantStatus status = MerchantStatus.ACTIVE;

    @Column(
            name = "created_at",
            nullable = false,
            updatable = false
    )
    private LocalDateTime createdAt;

    @Column(
            name = "updated_at",
            nullable = false
    )
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}