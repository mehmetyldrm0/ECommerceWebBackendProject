package com.project.ecommerce.app.service;

import com.project.ecommerce.app.dbo.CartResponse;
import com.project.ecommerce.app.entities.Cart;
import com.project.ecommerce.app.request.CartCreateRequest;
import com.project.ecommerce.app.request.CartItemRequest;
import com.project.ecommerce.app.request.CartUpdateRequest;
import com.project.ecommerce.app.request.RemoveProductFromCartRequest;

public interface CartService {
    CartResponse createCart(CartCreateRequest cartCreateRequest);
    CartResponse getCartByCustomerId(Long customerId);
    CartResponse updateCart(CartUpdateRequest request);
    CartResponse emptyCart(Long cartId);
    CartResponse addProductToCart(Long cartId, CartItemRequest request);
    CartResponse removeProductFromCart(RemoveProductFromCartRequest request);


}
