package com.example.shopspring.shop;

import com.example.shopspring.order.Order;
import com.example.shopspring.order.OrderRepo;
import com.example.shopspring.product.Product;
import com.example.shopspring.product.ProductRepo;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ShopServiceTest {
    ProductRepo productRepo = mock(ProductRepo.class);
    OrderRepo orderRepo = mock(OrderRepo.class);
    List<Product> products = List.of(
            new Product(1, "Apfel"),
            new Product(2, "Banane"),
            new Product(3, "Zitrone"),
            new Product(4, "Mandarine")
    );
    ShopService shopService = new ShopService(productRepo, orderRepo);

    @Test
    public void getProductServiceTest() {
        Product apfel = new Product(1, "Apfel");

        when(productRepo.getProduct(1))
                .thenReturn(apfel);
        Product actual = shopService.getProduct(1);
        assertEquals(apfel, actual);
    }

    @Test
    public void listProductsTest() {
        when(productRepo.listProducts())
                .thenReturn(products);

        List<Product> actual = shopService.listProducts();
        assertThat(actual, Matchers.containsInAnyOrder(products.toArray()));
    }

    @Test
    public void addOrderTest() {
        // new order to add to order repo :
        Order newOrder = new Order(1, List.of(products.get(0), products.get(1)));
        // ids of products included in the new order:
        List<Integer> productIdsToAdd = List.of(products.get(0).id(), products.get(1).id());
        // Mock productRepo.getRepo return value
        when(productRepo.getProduct(1)).thenReturn(products.get(0));
        when(productRepo.getProduct(2)).thenReturn(products.get(1));
        when(productRepo.getProduct(3)).thenReturn(products.get(2));

        // call addOrder for test:
        shopService.addOrder(1, productIdsToAdd);

        // check productRepo.getProduct called only twice in shopService.addOrder when new order has 2 ids:
        verify(productRepo, times(2)).getProduct(anyInt());
        // check orderRepo.addOrder called once when call shopService with right argument:
        verify(orderRepo, times(1)).addOrder(newOrder);
    }

    @Test
    public void deleteOrder() {
        shopService.deleteOrder(1);
        verify(orderRepo, times(1)).removeOrder(1);
    }

}