package ca.lazanomentsoa.notificationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class CartCreatedEvent {
    private Long cartId;
    private String userId;
    private String status;
    private List<String> randomString;
    private BigDecimal totalAmount;
    private LocalDateTime createdAt;

}
