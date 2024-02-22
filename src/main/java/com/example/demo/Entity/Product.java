package com.example.demo.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

//Product.java
@Getter
@Setter
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "product_id")
    private Long id;
    @Column(name = "category")
    String category;
    @Column(name = "name")
    String name;
    @Column(name = "country")
    String country;
    @Column(name = "brand")
    String brand;
    @Column(name = "gender")
    String gender;
    @Column(name = "color")
    String color;
    @Column(name = "shape")
    String shape;
    @Column(name = "image")
    String image;
    @Column(name = "lens_values")
    String lens_values;
    @Column(name = "price")
    String price;

    @OneToMany(mappedBy = "product")
    private List<BasketItem> basketItems;
}
