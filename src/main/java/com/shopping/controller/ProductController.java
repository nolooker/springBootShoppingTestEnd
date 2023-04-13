package com.shopping.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {

    @GetMapping(value = "/admin/products/new")
    public String productForm() {
        return "/product/prInsertForm" ;
    }
}