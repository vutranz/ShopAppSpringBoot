package com.project.shopapp.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class    OrderDTO {

    @JsonProperty("user_id")
    @Min(value = 1, message = "User id must be > 0")
    private Long userId;

    @JsonProperty("fullname")
    private String fullName;

    private String email;

    @JsonProperty("phone_number")
    @NotBlank(message = "phone number is reuired")
    private String phoneNumber;

    private String address;
    private String note;

    @JsonProperty("total_money")
    @Min(value = 0,message = "total money must be >= 0")
    private float totalMoney;

    @JsonProperty("shipped_method")
    private String shippingMethod;

    @JsonProperty("shipped_address")
    private String shippingAddress;

    @JsonProperty("shipped_data")
    private LocalDate shippingData;


    @JsonProperty("payment_method")
    private String paymentMethod;
}
