package com.project.ecommerce.app.service;

import com.project.ecommerce.app.entities.Cart;
import com.project.ecommerce.app.request.CartCreateRequest;
import com.project.ecommerce.app.request.CartItemRequest;
import com.project.ecommerce.app.request.CartUpdateRequest;
import com.project.ecommerce.app.request.RemoveProductFromCartRequest;

public interface CartRepositoryService {
    Cart createCart(CartCreateRequest cartCreateRequest);
    Cart getCartByCustomerId(Long customerId);
    void updateCart(CartUpdateRequest request);
    void emptyCart(Long cartId);
    void addProductToCart(Long cartId, CartItemRequest request);
    void removeProductFromCart(RemoveProductFromCartRequest request);


}
