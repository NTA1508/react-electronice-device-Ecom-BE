package com.backend_project.service;

import com.backend_project.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    public Product addProduct(Product product);
    public Product getProductById(int id);
    public Product updateProduct(Product product, int id);
    public void deleteProduct(int id);
    public List<Product> getAllProduct();

}
