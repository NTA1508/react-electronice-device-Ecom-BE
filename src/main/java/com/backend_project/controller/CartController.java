package com.backend_project.controller;

import com.backend_project.model.Cart;
import com.backend_project.model.Product;
import com.backend_project.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
@CrossOrigin
public class CartController {

    @Autowired
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
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
}
