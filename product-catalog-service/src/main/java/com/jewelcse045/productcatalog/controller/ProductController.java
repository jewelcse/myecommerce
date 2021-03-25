package com.jewelcse045.productcatalog.controller;


import com.jewelcse045.productcatalog.config.ProductConfig;
import com.jewelcse045.productcatalog.model.Product;
import com.jewelcse045.productcatalog.service.ProductService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @RabbitListener(queues = ProductConfig.PRODUCT_QUEUE)
    @PostMapping("/save/product")
    public void saveProduct(@RequestBody Product product){
        productService.saveOrUpdateProduct(product);
    }
    @GetMapping("/remove/product")
    public Map<String,Boolean> removeProduct(@RequestParam String id){
        Product product = productService.getProductById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + id));

        productService.removeProduct(product);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    /*
    User and Admin endpoint for get all product list
     */
    @GetMapping("/get/products")
    public ResponseEntity<List<Product>> getAllProduct(){
        List<Product> products = productService.getProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/get/product")
    public Optional<Product> getSingleProduct(@RequestParam String id){
        return productService.getProductById(id);
    }

    @PostMapping("/update/product/{id}")
    public ResponseEntity<Product> update(@RequestBody Product product, @PathVariable String id) {
        try {
            Product existingProduct = productService.getProductById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + id));

            product.setProductId(existingProduct.getProductId());
            productService.saveOrUpdateProduct(product);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }







}
