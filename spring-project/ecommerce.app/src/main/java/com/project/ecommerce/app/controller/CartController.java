package com.project.ecommerce.app.controller;

import com.project.ecommerce.app.entities.Cart;
import com.project.ecommerce.app.request.CartCreateRequest;
import com.project.ecommerce.app.request.CartItemRequest;
import com.project.ecommerce.app.request.CartUpdateRequest;
import com.project.ecommerce.app.request.RemoveProductFromCartRequest;
import com.project.ecommerce.app.service.CartRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/carts")
public class CartController {
    private CartRepositoryService cartRepositoryService;

    @Autowired
    public CartController(CartRepositoryService cartRepositoryService) {
        this.cartRepositoryService = cartRepositoryService;
    }

    @PostMapping
    public ResponseEntity<Cart> createCart(@RequestBody CartCreateRequest cartCreateRequest) {
        Cart createdCart = cartRepositoryService.createCart(cartCreateRequest);
        return new ResponseEntity<>(createdCart, HttpStatus.CREATED);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Cart> getCartByCustomerId(@PathVariable Long customerId) {
        Cart cart = cartRepositoryService.getCartByCustomerId(customerId);
        if (cart != null) {
            return new ResponseEntity<>(cart, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
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
