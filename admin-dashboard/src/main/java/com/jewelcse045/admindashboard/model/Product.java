package com.jewelcse045.admindashboard.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {


    private String productId;
    private String productTitle;
    private String productCategory;
    private String productDescription;
    private String productImage;
    private double productPrice;
}
