package com.ribeiro.faketwitter.config;

import com.ribeiro.faketwitter.entity.Role;
import com.ribeiro.faketwitter.entity.User;
import com.ribeiro.faketwitter.exception.ResourceNotFoundException;
import com.ribeiro.faketwitter.repository.RoleRepository;
import com.ribeiro.faketwitter.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Configuration
public class AdminUserConfig implements CommandLineRunner {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    private final Logger logger = LoggerFactory.getLogger(AdminUserConfig.class);

    public AdminUserConfig(
            RoleRepository roleRepository,
            UserRepository userRepository,
            BCryptPasswordEncoder passwordEncoder
    ) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) {
        var roleAmin = roleRepository
                .findByName(Role.Values.ADMIN.name())
                .orElseThrow(() -> new ResourceNotFoundException("role"));

        var userAdmin = userRepository.findByUsername("admin");
        userAdmin.ifPresentOrElse(
                user -> logger.info("admin user already exists"),
                () -> {
                    var user = new User();
                    user.setUsername("admin");
                    user.setPassword(passwordEncoder.encode("admin"));
                    user.setRoles(Set.of(roleAmin));
                    userRepository.save(user);
                }
        );
    }
}
