package ca.lazanomentsoa.gateway;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    // if the request is GET we can use GET, or POST use POST
    @RequestMapping("/products")
    public ResponseEntity<List<String>> productFallback(){
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(Collections.singletonList("Product service Unavailable, please try again later."));
    }
}
