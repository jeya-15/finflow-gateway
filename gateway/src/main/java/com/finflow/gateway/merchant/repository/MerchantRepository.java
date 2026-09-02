package com.finflow.gateway.merchant.repository;

import com.finflow.gateway.merchant.entity.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MerchantRepository extends JpaRepository<Merchant,Long > {

    Optional<Merchant> findByMerchantReference(String merchantReference);

    Optional<Merchant> findByUserId(Long userId);

    boolean existsByMerchantReference(String merchantReference);
}
