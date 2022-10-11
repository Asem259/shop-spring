package com.example.shopspring.Shop;

import com.example.shopspring.Exception.ItemNotFound;
import com.example.shopspring.order.Order;
import com.example.shopspring.order.OrderRepo;
import com.example.shopspring.product.Product;
import com.example.shopspring.product.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShopService {
    private final ProductRepo productRepo;
    private final OrderRepo orderRepo;

    public ShopService(
            ProductRepo productRepo,
            OrderRepo orderRepo) {
        this.productRepo = productRepo;
        this.orderRepo = orderRepo;
    }

    public Product getProduct(int id) {
        return productRepo.getProduct(id);
    }

    public List<Product> listProducts() {
        return productRepo.listProducts();
    }

    public void addOrder(int orderId, List<Integer> productIds) {
        List<Product> products = new ArrayList<>();
        for (int productId : productIds) {
            Product product = productRepo.getProduct(productId);
            products.add(product);
        }

        Order order = new Order(orderId, products);
        orderRepo.addOrder(order);
    }

    public Order getOrder(int orderId) {
        return orderRepo.getOrder(orderId);
    }

    public List<Order> listOrders() {
        return orderRepo.listOrders();
    }

    public void deleteOrder(int id) {
        try {
            orderRepo.removeOrder(id);
        }
        catch (ItemNotFound e){
            System.out.println(e.getMessage());
        }
    }
}