package com.qubitone.inventory_service.controller;

import com.qubitone.inventory_service.model.Product;
import com.qubitone.inventory_service.model.Category; // Added import
import com.qubitone.inventory_service.service.ProductService;
import com.qubitone.inventory_service.repository.CategoryRepository; // Added import
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;
    private final CategoryRepository categoryRepository; // 1. Added dependency field

    // 2. Updated constructor to inject both the service and the repository
    public ProductController(ProductService productService, CategoryRepository categoryRepository) {
        this.productService = productService;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public List<Product> getAll() {
        return productService.getAllProducts();
    }

    // Handles Multi-part Form Data (File + Fields)
    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<Product> createProduct(
            @RequestParam("title") String title,
            @RequestParam("price") Double price,
            @RequestParam("categoryId") Long categoryId, // 3. Added parameter to accept category ID
            @RequestParam(value = "image", required = false) MultipartFile image) throws IOException {
        
        // 4. Fetch the managed Category entity from the database
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found with ID: " + categoryId));

        Product product = new Product();
        product.setTitle(title);
        product.setPrice(price);
        product.setCategory(category); // 5. Established the Many-to-One relationship link
        
        Product saved = productService.saveProduct(product, image);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product product) {
        return ResponseEntity.ok(productService.updateProduct(id, product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Deleted successfully");
    }
}