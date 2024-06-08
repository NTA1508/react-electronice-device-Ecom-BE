package com.backend_project.controller;

import com.backend_project.model.FileModel;
import com.backend_project.model.Product;
import com.backend_project.repository.ProductRepository;
import com.backend_project.service.FileService;
import com.backend_project.service.ProductService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/product")
public class ProductController {

    @Value("${project.image}")
    private String path;

    @Autowired
    private ProductService productService;

    @Autowired
    private FileService fileService;

    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestBody Product product){
        return new ResponseEntity<Product>(productService.addProduct(product), HttpStatus.OK);
    }

    @GetMapping("getAll")
    public ResponseEntity<?> getAllProduct(){
        return new ResponseEntity<List<Product>>(productService.getAllProduct(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id){
        return productService.getProductById(id);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateFilm(@RequestBody Product product, @PathVariable int id){
        return new ResponseEntity<Product>(productService.updateProduct(product, id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable int id) throws IOException{
        Optional<Product> product = productRepository.findById(id);
        Path extractPath = Paths.get(path + File.separator + product.get().getProductImage());
        System.out.println(extractPath);
        try{
            Files.deleteIfExists(extractPath);
        }catch(Exception e){
            e.getMessage();
        }
        productService.deleteProduct(id);
        return "Delete Successfully";
    }

    @PostMapping("/post/{id}")
    public Product uploadImg(@RequestParam("image") MultipartFile image, @PathVariable int id) throws IOException{
        Product product = productService.getProductById(id);
        FileModel fileModel = fileService.uploadImg(path, image);
        product.setProductImage(fileModel.getImageFileName());
        return productService.updateProduct(product, id);
    }

    @GetMapping("/show/{id}")
    public void getImgFile(@PathVariable int id, HttpServletResponse response) throws IOException{
        Optional<Product> product = productRepository.findById(id);
        InputStream resource = fileService.getImgFile(path, product.get().getProductImage(), id);
        response.setContentType(MediaType.ALL_VALUE);
        StreamUtils.copy(resource, response.getOutputStream());
    }
}
