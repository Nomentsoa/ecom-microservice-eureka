package ca.lazanomentsoa.cartservice.controller;

import ca.lazanomentsoa.cartservice.dto.CreateItemRequest;
import ca.lazanomentsoa.cartservice.model.CartItem;
import ca.lazanomentsoa.cartservice.service.CartItemService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
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

    @RateLimiter(name = "rateLimiterBreaker", fallbackMethod = "getMessageFallback")
    @GetMapping("/test")
    public ResponseEntity<String> test(){
        return ResponseEntity.status(HttpStatus.OK).body("Hello Cart Service");
    }

    public ResponseEntity<String> getMessageFallback(Exception exception){
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Hello Fallback RateLimiter");
    }
}
