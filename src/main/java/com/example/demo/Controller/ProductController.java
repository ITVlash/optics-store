package com.example.demo.Controller;

import com.example.demo.Entity.Product;
import com.example.demo.Repository.BasketItemRepository;
import com.example.demo.Service.Implement.ProductServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


//ProductController.java
@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductServiceImplement productService;
    private final BasketItemRepository basketItemRepository;

    @Autowired
    public ProductController(ProductServiceImplement productServiceImplement, BasketItemRepository basketItemRepository) {
        this.productService = productServiceImplement;
        this.basketItemRepository = basketItemRepository;
    }

    @GetMapping
    public String getAllProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);

        Long basketItemCount = basketItemRepository.count();
        model.addAttribute("basketItemCount", basketItemCount);

        return "products/all";
    }

    @GetMapping("/{product_id}")
    public String getProduct(@PathVariable Long product_id, Model model) {
        Product product = productService.getProductById(product_id);
        model.addAttribute("product", product);

        Long basketItemCount = basketItemRepository.count();
        model.addAttribute("basketItemCount", basketItemCount);

        return "products/show";
    }
}
