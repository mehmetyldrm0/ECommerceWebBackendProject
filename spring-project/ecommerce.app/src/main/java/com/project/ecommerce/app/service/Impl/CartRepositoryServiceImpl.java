package com.project.ecommerce.app.service.Impl;

import com.project.ecommerce.app.entities.Cart;
import com.project.ecommerce.app.entities.CartItem;
import com.project.ecommerce.app.entities.Customer;
import com.project.ecommerce.app.repos.CartRepository;
import com.project.ecommerce.app.repos.CustomerRepository;
import com.project.ecommerce.app.request.CartCreateRequest;
import com.project.ecommerce.app.request.CartItemRequest;
import com.project.ecommerce.app.request.CartUpdateRequest;
import com.project.ecommerce.app.request.RemoveProductFromCartRequest;
import com.project.ecommerce.app.service.CartRepositoryService;
import com.project.ecommerce.app.service.CustomerRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CartRepositoryServiceImpl implements CartRepositoryService {

    private CartRepository cartRepository;
    private CustomerRepository customerRepository;



    @Autowired
    public CartRepositoryServiceImpl(CartRepository cartRepository, CustomerRepository customerRepository) {
        this.cartRepository = cartRepository;
        this.customerRepository = customerRepository;
    }


    @Override
    public Cart createCart(CartCreateRequest cartCreateRequest) {
        Cart cart = new Cart();

        Long customerId = cartCreateRequest.getCustomerId();
        Customer customer = customerRepository.findById(customerId).orElse(null);

        if (customer != null) {
            cart.setCustomer(customer);
            return cartRepository.save(cart);
        } else {
            // Eğer customer bulunamazsa isteğe bağlı olarak hata işlemleri yapılabilir.
            // Örneğin, bir hata mesajı döndürülebilir.
            throw new RuntimeException("Customer not found with id: " + customerId);
        }
    }

    @Override
    public Cart getCartByCustomerId(Long customerId) {
        return cartRepository.findById(customerId).orElse(null);
    }

    @Override
    public void updateCart(CartUpdateRequest request) {
        Cart cart = cartRepository.findById(request.getCartId()).orElse(null);

        if (cart != null) {
            List<CartItemRequest> cartItemRequests = request.getCartItemRequests();
            List<CartItem> updatedCartItemList = new ArrayList<>();

            for (CartItemRequest cartItemRequest : cartItemRequests) {
                CartItem cartItem = new CartItem();
                cartItem.setQuantity(cartItemRequest.getQuantity());
                updatedCartItemList.add(cartItem);
            }

            cart.setCartItemList(updatedCartItemList);
            cartRepository.save(cart);
        }
    }


    @Override
    public void emptyCart(Long cartId) {
        cartRepository.deleteById(cartId);
    }

    @Override
    public void addProductToCart(Long cartId, CartItemRequest request) {
        Cart cart = cartRepository.findById(cartId).orElse(null);

        if(cart != null) {
            CartItem cartItem = new CartItem();
            cartItem.setQuantity(request.getQuantity());
            List<CartItem> cartItemList = cart.getCartItemList();
            cartItemList.add(cartItem);
            cart.setCartItemList(cartItemList);

            cartRepository.save(cart);

        }
    }

    @Override
    public void removeProductFromCart(RemoveProductFromCartRequest request) {
        Cart cart = cartRepository.findById(request.getCartId()).orElse(null);

        if (cart != null) {
            List<CartItem> cartItemList = cart.getCartItemList();
            cartItemList.removeIf(cartItem -> cartItem.getProduct().getId().equals(request.getProductId()));

            cart.setCartItemList(cartItemList);

            cartRepository.save(cart);
        }

    }
}
