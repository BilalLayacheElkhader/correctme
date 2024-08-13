package com.correctme.correctme.model.repository;

import com.correctme.correctme.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String nickname);
    Optional<User> findPlayerByEmail(String email);
    boolean existsByName(String name);
}
