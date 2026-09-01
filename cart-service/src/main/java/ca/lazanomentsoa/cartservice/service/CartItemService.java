package ca.lazanomentsoa.cartservice.service;

import ca.lazanomentsoa.cartservice.clientConfig.product.ProductHttpInterface;
import ca.lazanomentsoa.cartservice.clientConfig.user.UserHttpInterface;
import ca.lazanomentsoa.cartservice.dto.CreateItemRequest;
import ca.lazanomentsoa.cartservice.repository.CartItemRepository;
import ca.lazanomentsoa.dto.product.ProductResponse;
import ca.lazanomentsoa.dto.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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





        return "";
    }
}
