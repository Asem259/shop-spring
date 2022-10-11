package com.example.shopspring.order;

import com.example.shopspring.shop.ShopService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final ShopService shopService;


    public OrderController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping()
    public List<Order> getAllOrders() {
        System.out.println("<<< Orders Controller get All >>>");
        return shopService.listOrders();
    }

    @GetMapping("{id}")
    public Order getSingleOrder(@PathVariable int id) {
        System.out.println("<<< Orders Controller get one >>>");
        return shopService.getOrder(id);
    }

    @DeleteMapping("{id}")
    public Order deleteOrder(@PathVariable int id) {
        System.out.println("<<< Orders Controller get one >>>");
        return shopService.getOrder(id);
    }

    @PostMapping
    public void addNewOrder(@RequestBody List<Integer> productsIds){
        System.out.println("<<< Orders Controller post new >>>");
        System.out.println(productsIds);
        Random rd = new Random();
        int  id =rd.nextInt();
        shopService.addOrder(id,productsIds );
    }

}
