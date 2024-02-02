package com.project.ecommerce.app.service.Impl;

import com.project.ecommerce.app.dbo.CartResponse;
import com.project.ecommerce.app.entities.Cart;
import com.project.ecommerce.app.entities.CartItem;
import com.project.ecommerce.app.repos.CartRepository;
import com.project.ecommerce.app.repos.CustomerRepository;
import com.project.ecommerce.app.repos.ProductRepository;
import com.project.ecommerce.app.request.CartCreateRequest;
import com.project.ecommerce.app.request.CartItemRequest;
import com.project.ecommerce.app.request.CartUpdateRequest;
import com.project.ecommerce.app.request.RemoveProductFromCartRequest;
import com.project.ecommerce.app.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CartRepositoryServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    @Override
    public CartResponse createCart(CartCreateRequest cartCreateRequest) {
        var cart = new Cart();

        var customerId = cartCreateRequest.getCustomerId();
        var customer = customerRepository.findById(customerId).orElse(null);

        if (customer != null)
            throw new RuntimeException("CustomerResponse not found with id: " + customerId);
        else {
            CartResponse cartResponse = CartResponse.builder()
                    .customer(customer)
                    .cartItemList(cart.getCartItemList())
                    .build();
            return cartResponse;
        }
    }

    @Override
    public CartResponse getCartByCustomerId(Long customerId) {
        Cart cart = cartRepository.findById(customerId).orElse(null);

        CartResponse cartResponse = CartResponse.builder()
                .customer(cart.getCustomer())
                .cartItemList(cart.getCartItemList())
                .build();

        return cartResponse;
    }

    @Override
    public CartResponse updateCart(CartUpdateRequest request) {
        var optionalCart = cartRepository.findById(request.getCartId());

        if (optionalCart.isPresent()) {
            var cart = optionalCart.get();
            var cartItemRequests = request.getCartItemRequests();
            List<CartItem> updatedCartItemList = new ArrayList<>();

            for (CartItemRequest cartItemRequest : cartItemRequests) {
                CartItem cartItem = new CartItem();
                cartItem.setProduct(productRepository.findById(cartItemRequest.getProductId()).orElse(null)); // Product set et

                cartItem.setQuantity(cartItemRequest.getQuantity());
                updatedCartItemList.add(cartItem);
            }

            cart.setCartItemList(updatedCartItemList);
            cartRepository.save(cart);

            CartResponse updatedCartResponse = CartResponse.builder()
                    .customer(cart.getCustomer())
                    .cartItemList(cart.getCartItemList())
                    .build();
            return updatedCartResponse;
        }

        return null;
    }

    @Override
    public CartResponse emptyCart(Long cartId) {
        var optionalCart = cartRepository.findById(cartId);

        if(optionalCart.isPresent()) {
            var cart = optionalCart.get();

            cart.getCartItemList().clear();
            cartRepository.save(cart);

            var cartResponse = CartResponse.builder()
                    .customer(cart.getCustomer())
                    .cartItemList(Collections.emptyList()) // Sepet boşaltıldığı için boş liste
                    .build();

            return cartResponse;
        }

        return null;
    }

    @Override
    public CartResponse addProductToCart(Long cartId, CartItemRequest request) {
        Cart cart = cartRepository.findById(cartId).orElse(null);

        if (cart != null) {
            CartItem cartItem = new CartItem();
            cartItem.setQuantity(request.getQuantity());
            List<CartItem> cartItemList = cart.getCartItemList();
            cartItemList.add(cartItem);
            cart.setCartItemList(cartItemList);

            cartRepository.save(cart);

            CartResponse cartResponse = CartResponse.builder()
                    .customer(cart.getCustomer())
                    .cartItemList(cart.getCartItemList())
                    .build();

            return cartResponse;
        }

        // Eğer belirtilen cartId'ye sahip bir Cart bulunamazsa veya ekleme işlemi başarısız olursa null dönebilir veya hata yönetimi yapabilirsiniz.
        return null;
    }

    @Override
    public CartResponse removeProductFromCart(RemoveProductFromCartRequest request) {
        Cart cart = cartRepository.findById(request.getCartId()).orElse(null);

        if (cart != null) {
            List<CartItem> cartItemList = cart.getCartItemList();
            cartItemList.removeIf(cartItem -> cartItem.getProduct().getId().equals(request.getProductId()));
            cart.setCartItemList(cartItemList);
            cartRepository.save(cart);

            CartResponse updatedCartResponse = CartResponse.builder()
                    .customer(cart.getCustomer())
                    .cartItemList(cart.getCartItemList())
                    .build();

            return updatedCartResponse;
        }

        // Eğer belirtilen cartId'ye sahip bir Cart bulunamazsa veya çıkarma işlemi başarısız olursa null dönebilir veya hata yönetimi yapabilirsiniz.
        return null;
    }
}
