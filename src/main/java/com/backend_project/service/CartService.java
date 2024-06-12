package com.backend_project.service;

import com.backend_project.model.Cart;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CartService {
    public void addToCart(int productId, String token);
    public List<Cart> getCartByUserId(String token);
}
