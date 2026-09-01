package ca.lazanomentsoa.cartservice.dto;

import lombok.Data;

@Data
public class CreateItemRequest {
    private Long productId;
    private Integer quantity;
}
