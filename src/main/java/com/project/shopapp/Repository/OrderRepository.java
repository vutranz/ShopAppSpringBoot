package com.project.shopapp.Repository;

import com.project.shopapp.Models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
   List<Order> findByUserId(Long userId);
}
