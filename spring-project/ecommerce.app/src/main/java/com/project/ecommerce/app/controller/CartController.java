package com.project.ecommerce.app.controller;

import com.project.ecommerce.app.dbo.CartResponse;
import com.project.ecommerce.app.request.CartCreateRequest;
import com.project.ecommerce.app.request.CartItemRequest;
import com.project.ecommerce.app.request.CartUpdateRequest;
import com.project.ecommerce.app.request.RemoveProductFromCartRequest;
import com.project.ecommerce.app.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/carts")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartRepositoryService;

    @PostMapping
    public ResponseEntity<CartResponse> createCart(@RequestBody CartCreateRequest cartCreateRequest) {
        var createdCart = cartRepositoryService.createCart(cartCreateRequest);
        return new ResponseEntity<>(createdCart, HttpStatus.CREATED);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CartResponse> getCartByCustomerId(@PathVariable Long customerId) {
        var cart = cartRepositoryService.getCartByCustomerId(customerId);

        if (cart != null) {
            return new ResponseEntity<>(cart, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{cartId}")
    public ResponseEntity<Void> updateCart(@PathVariable Long cartId, @RequestBody CartUpdateRequest request) {
        cartRepositoryService.updateCart(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<Void> emptyCart(@PathVariable Long cartId) {
        cartRepositoryService.emptyCart(cartId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{cartId}/products")
    public ResponseEntity<Void> addProductToCart(@PathVariable Long cartId, @RequestBody CartItemRequest request) {
        cartRepositoryService.addProductToCart(cartId, request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/products")
    public ResponseEntity<Void> removeProductFromCart(@RequestBody RemoveProductFromCartRequest request) {
        cartRepositoryService.removeProductFromCart(request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
