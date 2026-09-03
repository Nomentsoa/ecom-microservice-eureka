package ca.lazanomentsoa.cartservice.controller;

import ca.lazanomentsoa.cartservice.dto.CreateItemRequest;
import ca.lazanomentsoa.cartservice.model.CartItem;
import ca.lazanomentsoa.cartservice.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class CartItemController {

    private final CartItemService cartItemService;

    @PostMapping
    public ResponseEntity<String> addToCart(@RequestHeader("X-User-ID") String userId, @RequestBody CreateItemRequest request) {
        String response = cartItemService.addToCart(userId, request);
            return ResponseEntity.ok(response);
    }

    @GetMapping("/test")
    public ResponseEntity<String> test(){
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
