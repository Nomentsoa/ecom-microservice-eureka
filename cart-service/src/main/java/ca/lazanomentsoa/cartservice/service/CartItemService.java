package ca.lazanomentsoa.cartservice.service;

import ca.lazanomentsoa.cartservice.clientConfig.product.ProductHttpInterface;
import ca.lazanomentsoa.cartservice.clientConfig.user.UserHttpInterface;
import ca.lazanomentsoa.cartservice.dto.CartCreatedEvent;
import ca.lazanomentsoa.cartservice.dto.CreateItemRequest;
import ca.lazanomentsoa.cartservice.model.CartItem;
import ca.lazanomentsoa.cartservice.repository.CartItemRepository;
import ca.lazanomentsoa.dto.product.ProductResponse;
import ca.lazanomentsoa.dto.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CartItemService {
    private final CartItemRepository cartItemRepository;
    private final ProductHttpInterface productHttpInterface;
    private final UserHttpInterface userHttpInterface;

    private final RabbitTemplate rabbitTemplate;
    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;
    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    public String addToCart(String userId, CreateItemRequest createItemRequest) {

        ProductResponse product = productHttpInterface.getProduct(createItemRequest.getProductId().toString());

        if (product == null) {
            return "Product not found";
        }

        if (product.getStockQuantity() < createItemRequest.getQuantity()) {
            return "Product is too low";
        }

        UserResponse user = userHttpInterface.getUserById(userId);
        if (user == null) {
            return "User not found";
        }

//        CartItem cartItem = cartItemRepository.findByUserIdAndProductId(Long.valueOf(userId), createItemRequest.getProductId());
//        if(cartItem != null){
//            cartItem.setQuantity(cartItem.getQuantity() + createItemRequest.getQuantity());
//            cartItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())));
//            cartItemRepository.save(cartItem);
//        }else{
        CartItem cartItem = new CartItem();
        cartItem.setProductId(createItemRequest.getProductId());
        cartItem.setUserId(Long.valueOf(userId));
        cartItem.setQuantity(createItemRequest.getQuantity());
        cartItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(createItemRequest.getQuantity())));
        cartItemRepository.save(cartItem);
        //}

        //list ramdoms
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(String.valueOf("item " + i));
        }
    // public cart created event
        CartCreatedEvent cartCreatedEvent = new CartCreatedEvent(
                cartItem.getId(),
                cartItem.getUserId().toString(),
                "CREATED",
                list,
                cartItem.getPrice(),
                cartItem.getCreatedAt()
        );

//        rabbitTemplate.convertAndSend(exchangeName,
//                routingKey,
//                Map.of("cartId", cartItem.getId(), "status", "CREATED"));

        //send the event
        rabbitTemplate.convertAndSend(exchangeName, routingKey, cartCreatedEvent);

        return "Cart item saved";
    }
}
