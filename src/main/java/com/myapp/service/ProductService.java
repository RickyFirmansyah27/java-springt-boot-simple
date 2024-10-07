package com.myapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myapp.model.Entity.Product;
import com.myapp.model.Repository.ProductRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepo productRepository;

    public Product createOrUpdate(Product product) {
        return productRepository.save(product);
    }

    public Product findProductById(Long id) {
        return productRepository.findById(id).get();
    }

    public void deleteProduct(Product product, Long id) {
        productRepository.deleteById(id);
    }

    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    public List<Product> findByName(Product product, String name) {
        return productRepository.findByNameContains(name);
    }

    public void deleteProductById(Product product, Long id) {
        productRepository.deleteById(id);
    }
}
