package com.project.shopapp.Models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="social_accounts")
public class SocialAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name = "provider", nullable = false,length = 20)
    private String provider;

    @Column(name = "provider_id", nullable = false,length = 50)
    private String providerId;

    @Column(name = "email", nullable = false,length = 150)
    private String email;

    @Column(name = "name", nullable = false,length = 100)
    private String name;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
}


