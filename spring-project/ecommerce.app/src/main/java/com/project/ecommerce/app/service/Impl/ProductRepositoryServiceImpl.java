package com.project.ecommerce.app.service.Impl;

import com.project.ecommerce.app.entities.Product;
import com.project.ecommerce.app.entities.Stock;
import com.project.ecommerce.app.repos.ProductRepository;
import com.project.ecommerce.app.request.ProductCreateRequest;
import com.project.ecommerce.app.service.ProductRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductRepositoryServiceImpl implements ProductRepositoryService {
    private ProductRepository productRepository;
    private ProductRepositoryService productRepositoryService;

    @Lazy
    @Autowired
    public ProductRepositoryServiceImpl(ProductRepository productRepository, ProductRepositoryService productRepositoryService) {
        this.productRepository = productRepository;
        this.productRepositoryService = productRepositoryService;
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    @Override
    public Product createProduct(ProductCreateRequest productCreateRequest) {
        Product product = new Product();
        Stock stock = new Stock();

        product.setProductName(productCreateRequest.getProductName());
        product.setPrice(productCreateRequest.getPrice());
        stock.setProduct(product);
        stock.setQuantity(productCreateRequest.getQuantity());
        product.setStock(stock);

        productRepository.save(product);

        return product;



    }
    @Override
    public void updateProduct(Long id, Product updatedProduct) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isPresent()) {
            Product existingProduct  = optionalProduct.get();

            existingProduct.setProductName(updatedProduct.getProductName());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setStock(updatedProduct.getStock());
            productRepository.save(existingProduct);

        }
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
