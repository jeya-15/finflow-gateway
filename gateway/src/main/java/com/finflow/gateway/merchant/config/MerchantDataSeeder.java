package com.finflow.gateway.merchant.config;

import com.finflow.gateway.merchant.entity.Merchant;
import com.finflow.gateway.merchant.enums.BusinessType;
import com.finflow.gateway.merchant.enums.MerchantStatus;
import com.finflow.gateway.merchant.repository.MerchantRepository;
import com.finflow.gateway.user.entity.User;
import com.finflow.gateway.user.enums.Role;
import com.finflow.gateway.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MerchantDataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final MerchantRepository merchantRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        createMerchant(
                "freshbite@finflow.com",
                "Sourav",
                "FreshBite",
                BusinessType.FOOD
        );

        createMerchant(
                "urbanwear@finflow.com",
                "Shyam",
                "UrbanWear",
                BusinessType.CLOTHING
        );

        createMerchant(
                "techzone@finflow.com",
                "Jai",
                "TechZone",
                BusinessType.ELECTRONICS
        );

        createMerchant(
                "homenest@finflow.com",
                "Ajinesh",
                "HomeNest",
                BusinessType.HOME_APPLIANCES
        );

        createMerchant(
                "bookworld@finflow.com",
                "Muthu",
                "BookWorld",
                BusinessType.BOOKS
        );

        createMerchant(
                "fitgear@finflow.com",
                "Srivardhu",
                "FitGear",
                BusinessType.SPORTS
        );

        createMerchant(
                "beautyhub@finflow.com",
                "Maaran",
                "BeautyHub",
                BusinessType.BEAUTY
        );
    }

    private void createMerchant(
            String email,
            String fullName,
            String businessName,
            BusinessType businessType
    ) {

        if (userRepository.existsByEmail(email)) {
            return;
        }

        User user = User.builder()
                .email(email)
                .fullName(fullName)
                .passwordHash(passwordEncoder.encode("merchant123"))
                .role(Role.MERCHANT)
                .enabled(true)
                .build();

        userRepository.save(user);

        Merchant merchant = Merchant.builder()
                .user(user)
                .merchantReference(generateMerchantReference(businessName))
                .businessName(businessName)
                .businessType(businessType)
                .status(MerchantStatus.ACTIVE)
                .build();

        merchantRepository.save(merchant);
    }

    private String generateMerchantReference(String businessName) {
        return "MER-" + businessName.toUpperCase()
                .replace(" ", "-");
    }
}