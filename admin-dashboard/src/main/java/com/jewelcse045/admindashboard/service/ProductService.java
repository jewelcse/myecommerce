package com.jewelcse045.admindashboard.service;

import com.jewelcse045.admindashboard.model.Product;

import java.util.Map;

public interface ProductService {

    Map<String, Boolean> storeProduct(Product product);
}
