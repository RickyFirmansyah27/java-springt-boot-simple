package com.myapp.model.Repository;

import java.util.List;

// import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.myapp.model.Entity.Product;

public interface ProductRepo extends CrudRepository<Product, Long> {

    public List<Product> findByNameContains(String name);

    // custom query
    // @Query("SELECT p FROM Product p WHERE p.name LIKE %?1% OR p.description LIKE %?1%")
    // public List<Product> filterUser(String name, String email, Integer id);
}