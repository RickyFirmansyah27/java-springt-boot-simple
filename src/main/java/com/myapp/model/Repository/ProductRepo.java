package com.myapp.model.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.myapp.model.Entity.Product;

public interface ProductRepo extends CrudRepository<Product, Long> {

    public List<Product> findByNameContains(String name);
}