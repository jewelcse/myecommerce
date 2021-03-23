package com.jewelcse045.admindashboard.controller;


import com.jewelcse045.admindashboard.model.Product;
import com.jewelcse045.admindashboard.service.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;
import java.util.UUID;


@Controller
@RequestMapping("/admin")
public class ProductController {

    @Autowired
    private ProductServiceImp productServiceImp;

    @GetMapping("/add/product")
    public String addProduct(Model model){
        model.addAttribute("product", new Product());
        return "product/add-product";

    }

    @GetMapping("/get/products")
    public String getProducts(Model model){
        model.addAttribute("product", new Product());
        return "product/product-list";

    }

    @PostMapping("/store/product")
    public RedirectView save(@ModelAttribute("product") Product product){

        product.setProductId(UUID.randomUUID().toString());

        System.out.println(product);

        Map<String,Boolean> response;

        response = productServiceImp.storeProduct(product);
        System.out.println("Saving product => "+response);

        return new RedirectView("/admin/add/product");
    }


}
