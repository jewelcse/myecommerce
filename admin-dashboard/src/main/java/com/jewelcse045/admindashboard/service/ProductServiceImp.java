package com.jewelcse045.admindashboard.service;


import com.jewelcse045.admindashboard.config.ProductConfig;
import com.jewelcse045.admindashboard.model.Product;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ProductServiceImp  implements ProductService{

    @Autowired
    private RabbitTemplate template;


    @Override
    public Map<String, Boolean> storeProduct(Product product) {

        Map<String,Boolean> response = new HashMap<>();
        response.put("status",true);

        template.convertAndSend(ProductConfig.PRODUCT_EXCHANGE,
                ProductConfig.PRODUCT_ROUTING_KEY, product);

        return response;
    }
}
