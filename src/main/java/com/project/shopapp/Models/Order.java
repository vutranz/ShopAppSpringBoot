package com.project.shopapp.Models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="users_id")
    private User user;

    @Column(name = "fullname", length = 100)
    private String fullName;

    @Column(name="email", length = 100)
    private String email;

    @Column(name = "phone_number", nullable = false, length = 20)
    private String phoneNumber;

    @Column(name = "address", length = 255)
    private String address;

    @Column(name = "note" , length = 100)
    private String note;

    @Column(name = "order_date")
    private Date orderDate;

    @Column(name="status")
    private String status;

    @Column(name = "total_money")
    private float totalMoney;

    @Column(name = "shipped_method")
    private String shippedMethod;

    @Column(name = "shipped_address")
    private String shippedAddress;

    @Column(name = "shipped_date")
    private LocalDate shippedDate;

    @Column(name = "tracking_number")
    private String trackingNumber;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "active")
    private boolean active;
}
