package com.myapp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    public BaseResponse<Iterable<Product>> getListProduct() {
        logger.info("ProductController.getListProduct");
        var products = productService.findAll();

        if (!products.iterator().hasNext()) {
            logger.info("[ProductController.getProductById - error]");
            return new BaseResponse<>("true", "Product not found", products);
        }

        return new BaseResponse<>("success", "Product fetched successfully", products);
    }

    @GetMapping("/{id}")
    public BaseResponse<List<Product>> getProductById(@PathVariable("id") Long id) {
        logger.info("ProductController.getProductById");
        
        List<Product> products = productService.findProductById(id);
        
        if (products.isEmpty()) {
            logger.info("[ProductController.getProductById - error]");
            return new BaseResponse<>("error", "Product not found", products);
        }
        
        return new BaseResponse<>("success", "Product fetched successfully", products);
    }
    

    @PutMapping()
    public Product updateProduct(@RequestBody Product product) {
        logger.info("Endpoint '/api/products' ");
        return productService.createOrUpdate(product);
    }
    
    
}
