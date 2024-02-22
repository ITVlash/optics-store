package com.example.demo.Controller;

import com.example.demo.Entity.BasketItem;
import com.example.demo.Repository.BasketItemRepository;
import com.example.demo.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

//BasketController.java
@Controller
@RequestMapping("/basket")
public class BasketController {
    private final ProductService productService;
    private final BasketItemRepository basketItemRepository;

    @Autowired
    public BasketController(ProductService productService, BasketItemRepository basketItemRepository) {
        this.productService = productService;
        this.basketItemRepository = basketItemRepository;
    }

    @GetMapping
    public String getBasket(Model model) {
        List<BasketItem> basketItems = productService.getAllBasketItems();
        model.addAttribute("basketItems", basketItems);

        Long basketItemCount = basketItemRepository.count();
        model.addAttribute("basketItemCount", basketItemCount);
        return "basket";
    }

    @PostMapping("/add-catalog")
    public String addToCart(@RequestParam("productId") Long productId,
                            @RequestParam("amount") Long amount,
                            @RequestParam("path") String string) {
        productService.addToCart(productId, amount);
        return "redirect:/" + string;
    }

    @PostMapping("/add-cart")
    public String addToCart(@RequestParam("productId") Long productId,
                            @RequestParam("amount") Long amount,
                            @RequestParam("path") String string,
                            RedirectAttributes redirectAttributes) {
        productService.addToCart(productId, amount);
        redirectAttributes.addAttribute("productId", productId);
        return "redirect:/" + string + "/{productId}";
    }

    @PostMapping("/update")
    public String updateCartItemAmount(@RequestParam("basketItemId") Long basketItemId,
                                       @RequestParam("newAmount") Long newAmount) {
        productService.updateCartItemAmount(basketItemId, newAmount);
        return "redirect:/basket";
    }

    @PostMapping("/remove")
    public String removeCartItem(@RequestParam("basketItemId") Long basketItemId) {
        productService.removeCartItem(basketItemId);
        return "redirect:/basket";
    }
}

