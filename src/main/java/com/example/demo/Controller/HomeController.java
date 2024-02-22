package com.example.demo.Controller;

import com.example.demo.Entity.BasketItem;
import com.example.demo.Repository.BasketItemRepository;
import com.example.demo.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//HomeController.java
@Controller
@RequestMapping("/about")
public class HomeController {
    private final BasketItemRepository basketItemRepository;

    @Autowired
    public HomeController(ProductService productService, BasketItemRepository basketItemRepository) {
        this.basketItemRepository = basketItemRepository;
    }

    @GetMapping
    public String toAbout(Model model) {
        Long basketItemCount = basketItemRepository.count();
        model.addAttribute("basketItemCount", basketItemCount);
        return "about";
    }
}

