package com.example.shopspring.product;


import com.example.shopspring.Shop.ShopService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ShopService shopService;

    public ProductController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping()
    public List<Product> getAllProducts() {
        return shopService.listProducts();
    }

    @GetMapping("{id}")
    public Product getSingleProduct(@PathVariable int id) {
        return shopService.getProduct(id);
    }
}
