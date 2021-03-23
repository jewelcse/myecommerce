package com.jewelcse045.productcatalog.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    private String productId;
    private String productTitle;
    private String productCategory;
    private String productDescription;
    private String productImage;
    private double productPrice;
}
