package com.project.shopapp.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductImageDTO {

    @JsonProperty("product_id")
    @Min(value = 1,message = "product id must be > 0")
    private Long productId;

    @Size(min=5,max=200,message= "name must be between 3 and 200 characters")
    @JsonProperty("image_url")
    private String imageUrl;
}
