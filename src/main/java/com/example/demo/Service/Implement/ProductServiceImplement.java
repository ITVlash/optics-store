package com.example.demo.Service.Implement;

import com.example.demo.Entity.BasketItem;
import com.example.demo.Entity.Product;
import com.example.demo.Repository.BasketItemRepository;
import com.example.demo.Repository.ProductRepository;
import com.example.demo.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

// ProductServiceImplement.java
@Service
public class ProductServiceImplement implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImplement(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long productId) {
        return productRepository.
                findById(productId).
                orElseThrow(() -> new EntityNotFoundException("Product with id " + productId + "not found"));
    }


    @Autowired
    private BasketItemRepository basketItemRepository;

    @Override
    public List<BasketItem> getAllBasketItems() {
        return basketItemRepository.findAll();
    }

    @Override
    public BasketItem addToCart(Long productId, Long amount) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Товар с идентификатором " + productId + " не найден"));

        BasketItem basketItem = new BasketItem();
        basketItem.setProduct(product);
        basketItem.setAmount(amount);

        return basketItemRepository.save(basketItem);
    }

    @Override
    public BasketItem updateCartItemAmount(Long basketItemId, Long newAmount) {
        BasketItem basketItem = basketItemRepository.findById(basketItemId)
                .orElseThrow(() -> new EntityNotFoundException("Элемент корзины с идентификатором " + basketItemId + " не найден"));

        basketItem.setAmount(newAmount);

        return basketItemRepository.save(basketItem);
    }

    @Override
    public void removeCartItem(Long basketItemId) {
        basketItemRepository.deleteById(basketItemId);
    }
}
