package com.jewelcse045.admindashboard.service;

import com.jewelcse045.admindashboard.model.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ProductService {

    Map<String, Boolean> storeProduct(Product product);
    List<Product> getProducts();

    ResponseEntity<Product> removeProduct(String id);

    Product getSingleProduct(String productId);

    ResponseEntity<Product> updateProduct(Product product);
}
