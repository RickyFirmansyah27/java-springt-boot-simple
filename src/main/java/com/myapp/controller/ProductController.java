package com.myapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.model.Entity.Product;
import com.myapp.response.BaseResponse;
import com.myapp.service.ProductService;




@RestController
@RequestMapping("/api/products")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    
    @Autowired
    private ProductService productService;

    @PostMapping
    public Product create(@RequestBody Product product) {
        return productService.createOrUpdate(product);
    }

    @GetMapping()
    public ResponseEntity<?> getListProduct() {
        logger.info("Endpoint '/api/products' accessed");
        var products = productService.findAll();

        if (!products.iterator().hasNext()) {
            return ResponseEntity.ok(new BaseResponse<>("success", "No products found", products));
        }

        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public BaseResponse<Product> getProductById(@PathVariable ("id") Long id) {
        logger.info("Fetching product with ID: {}", id);
        var product = productService.findProductById(id);            
        return new BaseResponse<>("success", "Users fetched successfully", product);
    }

    @PutMapping()
    public Product updateProduct(@RequestBody Product product) {
        logger.info("Endpoint '/api/products' ");
        return productService.createOrUpdate(product);
    }
    
    
}
