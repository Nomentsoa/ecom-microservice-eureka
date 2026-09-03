package ca.lazanomentsoa.cartservice.service;

import ca.lazanomentsoa.cartservice.clientConfig.product.ProductHttpInterface;
import ca.lazanomentsoa.cartservice.clientConfig.user.UserHttpInterface;
import ca.lazanomentsoa.cartservice.dto.CreateItemRequest;
import ca.lazanomentsoa.cartservice.model.CartItem;
import ca.lazanomentsoa.cartservice.repository.CartItemRepository;
import ca.lazanomentsoa.dto.product.ProductResponse;
import ca.lazanomentsoa.dto.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CartItemService {
    private final CartItemRepository cartItemRepository;
    private final ProductHttpInterface productHttpInterface;
    private final UserHttpInterface userHttpInterface;

    public String addToCart(String userId, CreateItemRequest createItemRequest){

        ProductResponse product = productHttpInterface.getProduct(createItemRequest.getProductId().toString());

        if(product == null){
            return "Product not found";
        }

        if(product.getStockQuantity() < createItemRequest.getQuantity()){
            return "Product is too low";
        }

        UserResponse user = userHttpInterface.getUserById(userId);
        if(user == null){
            return "User not found";
        }

        CartItem cartItem = cartItemRepository.findByUserIdAndProductId(Long.valueOf(userId), createItemRequest.getProductId());
        if(cartItem != null){
            cartItem.setQuantity(cartItem.getQuantity() + createItemRequest.getQuantity());
            cartItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())));
            cartItemRepository.save(cartItem);
        }else{
            CartItem newCartItem = new CartItem();
            newCartItem.setProductId(createItemRequest.getProductId());
            newCartItem.setUserId(Long.valueOf(userId));
            newCartItem.setQuantity(createItemRequest.getQuantity());
            newCartItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(createItemRequest.getQuantity())));
            cartItemRepository.save(newCartItem);
        }

        return "Cart item saved";
    }
}
