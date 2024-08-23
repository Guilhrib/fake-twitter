package com.ribeiro.faketwitter.service.impl;

import com.ribeiro.faketwitter.controller.dto.CreateUser;
import com.ribeiro.faketwitter.controller.dto.UserResponse;
import com.ribeiro.faketwitter.entity.Role;
import com.ribeiro.faketwitter.entity.User;
import com.ribeiro.faketwitter.exception.InternalErrorException;
import com.ribeiro.faketwitter.exception.ResourceAlreadyExists;
import com.ribeiro.faketwitter.exception.ResourceNotFoundException;
import com.ribeiro.faketwitter.repository.RoleRepository;
import com.ribeiro.faketwitter.repository.UserRepository;
import com.ribeiro.faketwitter.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(
            UserRepository userRepository,
            RoleRepository roleRepository,
            BCryptPasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findById(String userId) {
        return userRepository
                .findById(UUID.fromString(userId))
                .orElseThrow(() -> new ResourceNotFoundException("user"));
    }

    @Override
    public User findByUsername(String username) {
        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("user"));
    }

    @Override
    @Transactional
    public void create(CreateUser dto) {
        try {
            findByUsername(dto.username());
            throw new ResourceAlreadyExists("user");
        } catch (ResourceAlreadyExists ex) {
            throw ex;
        } catch (ResourceNotFoundException ignored) {
            // this is the successful path we expected
        } catch (Exception ex) {
            throw new InternalErrorException();
        }

        var basicRole = roleRepository
                .findByName(Role.Values.BASIC.name())
                .orElseThrow(() -> new ResourceNotFoundException("role"));

        var user = new User();
        user.setUsername(dto.username());
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setRoles(Set.of(basicRole));
        userRepository.save(user);
    }

    @Override
    public List<UserResponse> listUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(UserResponse::new)
                .toList();
    }
}
