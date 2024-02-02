package com.project.ecommerce.app.service;

import com.project.ecommerce.app.dbo.ProductResponse;
import com.project.ecommerce.app.entities.Product;
import com.project.ecommerce.app.request.ProductCreateRequest;

import java.util.List;

public interface ProductService {
    ProductResponse getProductById(Long id);
    List<ProductResponse> getAllProducts();
    ProductResponse createProduct(ProductCreateRequest productCreateRequest);
    ProductResponse updateProduct(Long id, Product updatedProduct);
    ProductResponse deleteProduct(Long id);
}
