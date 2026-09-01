package ca.lazanomentsoa.productservice.service;

import ca.lazanomentsoa.dto.product.ProductResponse;
import ca.lazanomentsoa.productservice.dto.ProductRequest;
import ca.lazanomentsoa.productservice.mapper.ProductMapper;
import ca.lazanomentsoa.productservice.models.Product;
import ca.lazanomentsoa.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public ProductResponse createProduct(ProductRequest productRequest) {
        return ProductMapper.toProductResponse(productRepository.save(ProductMapper.toProduct(productRequest)));
    }

    public Optional<ProductResponse> getProductResponseById(Long id){
        return productRepository.findById(id)
                .map(ProductMapper::toProductResponse);

    }
}
