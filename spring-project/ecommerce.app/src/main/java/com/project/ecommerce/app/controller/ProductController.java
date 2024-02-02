package com.project.ecommerce.app.controller;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.ecommerce.app.entities.Product;
import com.project.ecommerce.app.request.ProductCreateRequest;
import com.project.ecommerce.app.service.ProductRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/products")
public class ProductController {
    private ProductRepositoryService productRepositoryService;

    @Autowired
    public ProductController(ProductRepositoryService productRepositoryService) {
        this.productRepositoryService = productRepositoryService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productRepositoryService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productRepositoryService.getProductById(id);
        return product != null ? new ResponseEntity<>(product, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductCreateRequest productCreateRequest) {
        Product createdProduct = productRepositoryService.createProduct(productCreateRequest);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        productRepositoryService.updateProduct(id, updatedProduct);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productRepositoryService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
