package com.jewelcse045.productcatalog.service;


import com.jewelcse045.productcatalog.model.Product;
import com.jewelcse045.productcatalog.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImp implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product saveOrUpdateProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> getProductById(String id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAllByOrderByProductIdDesc();
    }

    @Override
    public void removeProduct(Product product) {
        productRepository.delete(product);
    }


}
