package com.example.shopspring.order;

import com.example.shopspring.product.Product;

import java.util.List;

public record Order(
        int id,
        List<Product> products
) {
}