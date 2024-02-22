package com.example.demo.Repository;

import com.example.demo.Entity.BasketItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//BasketItemRepository.java
@Repository
public interface BasketItemRepository extends JpaRepository<BasketItem, Long> {
}
