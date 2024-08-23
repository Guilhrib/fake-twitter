package com.ribeiro.faketwitter.repository;

import com.ribeiro.faketwitter.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<User, UUID> {
}
