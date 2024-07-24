package com.project.shopapp.Models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(name="order_details")
public class OrderDetail{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="orders_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name="products_id")
    private Product product;

    @Column(name="price", nullable=false)
    private float price;

    @Column(name="number_of_products", nullable=false)
    private int numberOfProduct;

    @Column(name="total_money", nullable = false)
    private float totalMoney;

    @Column(name="color")
    private String color;


}
