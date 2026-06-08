package com.qubitone.inventory_service.repository;

import com.qubitone.inventory_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email); // Used to check login credentials
}