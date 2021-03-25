package com.jewelcse045.admindashboard.service;


import com.jewelcse045.admindashboard.config.ProductConfig;
import com.jewelcse045.admindashboard.constant.RequestURL;
import com.jewelcse045.admindashboard.model.Product;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImp  implements ProductService,RequestURL {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private RestTemplate restTemplate;



    @Override
    public Map<String, Boolean> storeProduct(Product product) {

        Map<String,Boolean> response = new HashMap<>();
        response.put("status",true);

        template.convertAndSend(ProductConfig.PRODUCT_EXCHANGE,
                ProductConfig.PRODUCT_ROUTING_KEY, product);

        return response;
    }

    @Override
    public List<Product> getProducts() {

        ResponseEntity<Product[]> responseEntity
                = restTemplate.getForEntity(RequestURL.FETCH_PRODUCTS_URL, Product[].class);

        List<Product> products = Arrays.asList(responseEntity.getBody());

        return products;
    }

    @Override
    public ResponseEntity<Product> removeProduct(String id) {
        System.out.println("remove service");

        try {

            ResponseEntity<Product> productResponseEntity
                    = restTemplate.getForEntity(RequestURL.REMOVE_SINGLE_PRODUCT_URL+id,Product.class);

            HttpStatus status = productResponseEntity.getStatusCode();

            if (status == HttpStatus.OK) {
                return productResponseEntity;
            }

        }catch (HttpStatusCodeException e){
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                return null;
            }
            throw e;
        }
        return null;

    }



    @Override
    public Product getSingleProduct(String productId) {
        ResponseEntity<Product> responseEntity = restTemplate.getForEntity(RequestURL.FETCH_SINGLE_PRODUCT_URL+productId,Product.class);

        Product product = responseEntity.getBody();

        return product;
    }

    @Override
    public ResponseEntity<Product> updateProduct(Product product) {

        Map<String, String> uriParams = new HashMap<>();
        uriParams.put("id",product.getProductId());

        System.out.println("service "+product);
        try {

            // set headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            // set request
            HttpEntity<Product> request
                    = new HttpEntity<>(product, headers);


            // post request
            ResponseEntity<Product> responseEntity
                    = restTemplate.postForEntity(RequestURL.PRODUCT_UPDATE_URL, request, Product.class,uriParams);

            HttpStatus status = responseEntity.getStatusCode();

            if (status == HttpStatus.OK) {
                return responseEntity;
            }

        }catch (HttpStatusCodeException e){
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                return null;
            }
            throw e;
        }
        return null;
    }







}
