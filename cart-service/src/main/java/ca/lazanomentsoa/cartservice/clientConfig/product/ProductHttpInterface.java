package ca.lazanomentsoa.cartservice.clientConfig.product;

import ca.lazanomentsoa.dto.product.ProductResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange("/api/products")
public interface ProductHttpInterface {

    @GetExchange("/{id}")
    ProductResponse getProduct(@PathVariable("id") String id);

}
