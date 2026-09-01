package ca.lazanomentsoa.productservice.mapper;

import ca.lazanomentsoa.dto.product.ProductResponse;
import ca.lazanomentsoa.productservice.dto.ProductRequest;
import ca.lazanomentsoa.productservice.models.Product;

public class ProductMapper {
    public static ProductResponse toProductResponse(Product product){
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setDescription(product.getDescription());
        productResponse.setPrice(product.getPrice());
        productResponse.setActive(product.getActive());
        productResponse.setImageUrl(product.getImageUrl());
        productResponse.setCategory(product.getCategory());
        productResponse.setStockQuantity(product.getStockQuantity());
        return productResponse;
    }

    public static Product toProduct(ProductRequest productRequest){
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setCategory(productRequest.getCategory());
        product.setImageUrl(productRequest.getImageUrl());
        product.setStockQuantity(productRequest.getStockQuantity());

        return product;
    }
}
