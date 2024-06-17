package com.backend_project.controller;

import com.backend_project.model.Cart;
import com.backend_project.model.Product;
import com.backend_project.repository.CartRepository;
import com.backend_project.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/cart")
@CrossOrigin
public class CartController {

    @Autowired
    private final CartService cartService;

    @Autowired
    private final CartRepository cartRepository;

    public CartController(CartService cartService, CartRepository cartRepository) {
        this.cartService = cartService;
        this.cartRepository = cartRepository;
    }

    @PostMapping("/addCart/{productId}/{token}")
    public void addToCart(@PathVariable int productId, @PathVariable String token){
        cartService.addToCart(productId, token);
    }

    @GetMapping("/getCart/{token}")
    public ResponseEntity<List<Cart>> getCartByUserId(@PathVariable String token) {
        List<Cart> cartItems = cartService.getCartByUserId(token);
        return ResponseEntity.ok(cartItems);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable int id) throws IOException {
        Optional<Cart> cart = cartRepository.findById(id);
        cartService.deleteCart(id);
        return "Delete Successfully";
    }
}
