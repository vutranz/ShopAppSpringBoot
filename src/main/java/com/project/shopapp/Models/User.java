package com.project.shopapp.Models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Table(name="users")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name = "fullname", length = 255)
    private String fullName;

    @Column(name = "phone_number", nullable = false, length = 255)
    private String phoneNumber;

    @Column(name = "address", nullable = true, length = 255)
    private String address;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name="is_active")
    private boolean active;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "facebook_account_id")
    private int facebookAccountId;

    @Column(name = "google_account_id")
    private int googleAccountId;

    @ManyToOne
    @JoinColumn(name="role_id")
    private Role role;


}

