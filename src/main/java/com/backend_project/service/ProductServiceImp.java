package com.backend_project.service;

import com.backend_project.exception.ResourceNotFoundException;
import com.backend_project.model.Product;
import com.backend_project.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImp implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product addProduct(Product product) {
        if(product.getProductName().isEmpty()){
            throw  new ResourceNotFoundException(false, "product name can not be empty");
        }
        try{
            product.setCreateAt(new Date());
            return productRepository.save(product);
        }catch (Exception e){
            throw  new ResourceNotFoundException(false, "something worng");
        }
    }

    @Override
    public Product getProductById(int id) {
        return productRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(false, "product not found"));
    }

    @Override
    public Product updateProduct(Product product, int id) {
        Product p = productRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(false, "product not found"));
        p.setProductImage(product.getProductImage());
        p.setProductName(product.getProductName());
        p.setType(product.getType());
        p.setDescription(product.getDescription());
        p.setPrice(product.getPrice());
        p.setRate(product.getRate());
        p.setSales(product.getSales());
        p.setSaleType(product.getSaleType());
        p.setStorageAddress(product.getStorageAddress());
        p.setStockNumber(product.getStockNumber());
        p.setCreateAt(new Date());

        return productRepository.save(p);
    }

    @Override
    public void deleteProduct(int id) {
        Product product = productRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(false, "product not found"));
        productRepository.delete(product);
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }
}
