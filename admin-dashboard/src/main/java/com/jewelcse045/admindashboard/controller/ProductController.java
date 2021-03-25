package com.jewelcse045.admindashboard.controller;


import com.jewelcse045.admindashboard.model.Product;
import com.jewelcse045.admindashboard.service.ProductServiceImp;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;
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
    @HystrixCommand(fallbackMethod = "getFallBackProducts")
    public String getProducts(Model model){

        List<Product> products = productServiceImp.getProducts();

        model.addAttribute("products", products);
        return "product/product-list";

    }

    public String getFallBackProducts(Model model){

        List<Product> products = new ArrayList<>();

        model.addAttribute("products",products);
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

    @GetMapping("/product/remove/{id}")
    public RedirectView remove(@PathVariable("id") String id){

        ResponseEntity<Product> response = productServiceImp.removeProduct(id);
        System.out.println("Deleting product where ID "+ id +" "+" => "+response);

        return new RedirectView("/admin/get/products");
    }

    @GetMapping("/product/edit/{id}")
    public String updateProduct(Model model,@PathVariable("id") String productId){

        Product product = productServiceImp.getSingleProduct(productId);


        model.addAttribute("product",product);


        System.out.println("fetching.."+product);
        return "product/edit-product";
    }


    @PostMapping("/product/update/{id}")
    public RedirectView update(@ModelAttribute("product") @PathVariable("id") String id, Product product){

        System.out.println("updating "+id);
        product.setProductId(id);
        System.out.println(product);

        ResponseEntity<Product> response = productServiceImp.updateProduct(product);
        System.out.println("Updating product => "+response);

        return new RedirectView("/admin/get/products");
    }




}
