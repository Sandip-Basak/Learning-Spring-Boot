package com.qubitone.inventory_service.repository;

import com.qubitone.inventory_service.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    // JpaRepository provides built-in methods: save(), findById(), findAll(), deleteById()
    // You do not need to write any implementation code here!
}