package com.project.ecommerce.app.service;

import com.project.ecommerce.app.entities.Product;
import com.project.ecommerce.app.request.ProductCreateRequest;

import java.util.List;

public interface ProductRepositoryService {
    Product getProductById(Long id);
    List<Product> getAllProducts();
    Product createProduct(ProductCreateRequest productCreateRequest);
    void updateProduct(Long id, Product updatedProduct);
    void deleteProduct(Long id);
}
