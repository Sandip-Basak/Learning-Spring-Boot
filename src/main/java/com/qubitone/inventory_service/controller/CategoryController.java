package com.qubitone.inventory_service.controller;

import com.qubitone.inventory_service.model.Category;
import com.qubitone.inventory_service.repository.CategoryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryRepository categoryRepository;

    // Constructor Injection to link our repository layer interface
    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // 1. GET Endpoint: Returns all categories from MySQL to populate your dropdown menu
    @GetMapping
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    // 2. POST Endpoint: Accepts a raw JSON request containing the category name and saves it
    @PostMapping
    public Category create(@RequestBody Category category) {
        return categoryRepository.save(category);
    }
}