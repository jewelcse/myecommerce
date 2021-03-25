package com.jewelcse045.productcatalog.service;


import com.jewelcse045.productcatalog.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Product saveOrUpdateProduct(Product product);
    Optional<Product> getProductById(String id);
    List<Product> getProducts();
    void removeProduct(Product product);
}
