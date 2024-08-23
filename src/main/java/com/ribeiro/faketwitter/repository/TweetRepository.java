package com.ribeiro.faketwitter.repository;

import com.ribeiro.faketwitter.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TweetRepository extends JpaRepository<Role, Long> {
}
