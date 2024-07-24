package com.project.shopapp.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.shopapp.Models.Order;
import com.project.shopapp.Models.OrderDetail;
import com.project.shopapp.Models.Product;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderDetailResponse  {

    private Long id;

    @JsonProperty("orders_id")
    private Long orderId;

    @JsonProperty("products_id")
    private Long productId;

    @JsonProperty("price")
    private float price;

    @JsonProperty("number_of_products")
    private int numberOfProduct;

    @JsonProperty("total_money")
    private float totalMoney;

    @JsonProperty("color")
    private String color;

    public static OrderDetailResponse fromOrderDatail(OrderDetail orderDetail)
    {
        return OrderDetailResponse
                .builder()
                .id(orderDetail.getId())
                .orderId(orderDetail.getOrder().getId())
                .productId(orderDetail.getProduct().getId())
                .price(orderDetail.getPrice())
                .numberOfProduct(orderDetail.getNumberOfProduct())
                .totalMoney(orderDetail.getTotalMoney())
                .color(orderDetail.getColor())
                .build();
    }
}
