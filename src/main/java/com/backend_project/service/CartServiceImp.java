package com.backend_project.service;

import com.backend_project.exception.ResourceNotFoundException;
import com.backend_project.model.Cart;
import com.backend_project.model.Product;
import com.backend_project.model.User;
import com.backend_project.repository.CartRepository;
import com.backend_project.repository.ProductRepository;
import com.backend_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImp implements CartService{

    @Autowired
    private final CartRepository cartRepository;

    @Autowired
    private final ProductRepository productRepository;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final JwtService jwtService;

    public CartServiceImp(CartRepository cartRepository, ProductRepository productRepository, UserRepository userRepository, JwtService jwtService) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @Override
    public void addToCart(int productId, String token) {
        Product product = productRepository.findById(productId).orElseThrow(()->new ResourceNotFoundException(false, "product not found"));
        int userId = jwtService.extractUserId(token);
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException(false, "user not found"));

        Cart cart = new Cart(product, user, 1);
        cartRepository.save(cart);
    }

    @Override
    public List<Cart> getCartByUserId(String token) {
        int userId = jwtService.extractUserId(token);
        return cartRepository.findByUserId(userId);
    }

    @Override
    public void deleteCart(int id) {
        Cart cart = cartRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(false, "cart not found"));
        cartRepository.delete(cart);
    }
}
