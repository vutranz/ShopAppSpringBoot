package com.project.shopapp.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDTO {

    @JsonProperty("order_id")
    @Min(value = 1, message = "order id must be > 0")
    private Long orderId;

    @JsonProperty("product_id")
    @Min(value = 1, message = "product id must be > 0")
    private Long productId;

    @JsonProperty("price")
    @Min(value = 0, message = "price must be >= 0")
    private float price;

    @JsonProperty("number_of_products")
    @Min(value = 1, message = "number Of Product id must be > 0")
    private int numberOfProduct;

    @JsonProperty("total_money")
    @Min(value = 0, message = "total Money must be >= 0")
    private float totalMoney;

    private String color;
}
