package com.project.ecommerce.app.controller;


import com.project.ecommerce.app.dbo.ProductResponse;
import com.project.ecommerce.app.entities.Product;
import com.project.ecommerce.app.request.ProductCreateRequest;
import com.project.ecommerce.app.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productRepositoryService;

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        var products = productRepositoryService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {
        var product = productRepositoryService.getProductById(id);
        return product != null ? new ResponseEntity<>(product, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductCreateRequest productCreateRequest) {
        var createdProduct = productRepositoryService.createProduct(productCreateRequest);
        return new ResponseEntity<>(createdProduct, HttpStatus.OK);
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
