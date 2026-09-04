package com.finflow.gateway.user.config;

import com.finflow.gateway.user.entity.User;
import com.finflow.gateway.user.enums.Role;
import com.finflow.gateway.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminDataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        createAdmin("admin@finflow.com","Jeya Athithya");
    }

    private void createAdmin(String email,String fullName){
        if(userRepository.existsByEmail(email)){
            return;
        }

        User user = User.builder()
                .email(email)
                .fullName(fullName)
                .passwordHash(passwordEncoder.encode("Admin@123"))
                .role(Role.ADMIN)
                .build();
        userRepository.save(user);
    }
}
