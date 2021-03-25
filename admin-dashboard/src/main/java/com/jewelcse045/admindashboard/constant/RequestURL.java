package com.jewelcse045.admindashboard.constant;



public interface RequestURL {

    String PRODUCT_SERVICE_BASE_URL
            ="http://localhost:8000";


    String FETCH_PRODUCTS_URL=PRODUCT_SERVICE_BASE_URL+"/get/products";
    String PRODUCT_UPDATE_URL = PRODUCT_SERVICE_BASE_URL+"/update/product/{id}";
    String FETCH_SINGLE_PRODUCT_URL = PRODUCT_SERVICE_BASE_URL+"/get/product?id=";
    String REMOVE_SINGLE_PRODUCT_URL = PRODUCT_SERVICE_BASE_URL+"/remove/product?id=";
}
