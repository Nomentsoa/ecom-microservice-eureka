package ca.lazanomentsoa.productservice.controller;

import ca.lazanomentsoa.dto.product.ProductResponse;
import ca.lazanomentsoa.productservice.dto.ProductRequest;
import ca.lazanomentsoa.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;



    @GetMapping("/simulate")
    public ResponseEntity<String> simulateFailure(@RequestParam(defaultValue = "false") boolean fail){
        if(fail){
            throw new RuntimeException("Simulated failure for testing");
        }
        return ResponseEntity.ok("Product Service is OK");
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest) {
        return ResponseEntity.ok(productService.createProduct(productRequest));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProductsResponse());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable("id") String id){
        return productService.getProductResponseById(Long.valueOf(id))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
