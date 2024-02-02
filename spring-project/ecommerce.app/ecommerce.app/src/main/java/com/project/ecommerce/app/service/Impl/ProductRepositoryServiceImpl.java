package com.project.ecommerce.app.service.Impl;

import com.project.ecommerce.app.dbo.ProductResponse;
import com.project.ecommerce.app.entities.Product;
import com.project.ecommerce.app.entities.Stock;
import com.project.ecommerce.app.repos.ProductRepository;
import com.project.ecommerce.app.request.ProductCreateRequest;
import com.project.ecommerce.app.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductRepositoryServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public ProductResponse getProductById(Long id) {
        var product = productRepository.findById(id).orElse(null);

        return ProductResponse.builder()
                .productName(product.getProductName())
                .price(product.getPrice())
                .stock(product.getStock())
                .priceHistory(product.getPriceHistory())
                .build();

    }

    @Override
    public List<ProductResponse> getAllProducts() {
        var products = productRepository.findAll();
        List<ProductResponse> productResponses = new ArrayList<>();

        for (Product product : products) {
            ProductResponse productResponse = ProductResponse.builder()
                    .productName(product.getProductName())
                    .price(product.getPrice())
                    .priceHistory(product.getPriceHistory())
                    .stock(product.getStock())
                    .build();
            productResponses.add(productResponse);
        }
        return productResponses;
    }

    @Override
    public ProductResponse createProduct(ProductCreateRequest productCreateRequest) {
        var product = new Product();
        var stock = new Stock();

        product.setProductName(productCreateRequest.getProductName());
        product.setPrice(productCreateRequest.getPrice());
        stock.setProduct(product);
        stock.setQuantity(productCreateRequest.getQuantity());
        product.setStock(stock);


        productRepository.save(product);

        ProductResponse productResponse = ProductResponse.builder()
                .productName(product.getProductName())
                .price(product.getPrice())
                .priceHistory(product.getPriceHistory())
                .stock(product.getStock())
                .build();


        return productResponse;
    }

    @Override
    public ProductResponse updateProduct(Long id, Product updatedProduct) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();

            existingProduct.setProductName(updatedProduct.getProductName());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setStock(updatedProduct.getStock());

            productRepository.save(existingProduct);

            ProductResponse updatedProductResponse = getProductById(id);

            return updatedProductResponse;
        }

        return null;
    }


    @Override
    public ProductResponse deleteProduct(Long id) {

        ProductResponse deletedProductResponse = getProductById(id);

        productRepository.deleteById(id);

        return deletedProductResponse;

    }
}
