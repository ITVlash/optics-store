package com.example.demo.Service;

import com.example.demo.Entity.BasketItem;
import com.example.demo.Entity.Product;

import java.util.List;

//ProductService.java
public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(Long productId);

    List<BasketItem> getAllBasketItems();
    BasketItem addToCart(Long productId, Long amount);
    BasketItem updateCartItemAmount(Long basketItemId, Long newAmount);
    void removeCartItem(Long basketItemId);
}
