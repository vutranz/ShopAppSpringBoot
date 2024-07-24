package com.project.shopapp.Models;

import jakarta.persistence.*;
import lombok.*;

@Table(name="product_images")
@Entity
@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;

    @Column(name = "img_url", length = 300)
    private String imageUrl;
}
