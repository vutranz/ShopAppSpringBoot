package com.project.shopapp.Models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
public class Product extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name="id")
    private Long id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    private Float price;

    @Column(name = "thumbnail", length = 255)
    private String thumbnail;

    @Column(name = "description")
    private String description;


    @ManyToOne
    @JoinColumn(name="categories_id ")
    private Category category;
}
