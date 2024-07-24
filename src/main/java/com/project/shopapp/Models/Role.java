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
@Table(name="roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name = "name", nullable = false,length = 50)
    private String name;

    public static String ADMIN = "ADMIN";
    public static String USER = "USER";
}
