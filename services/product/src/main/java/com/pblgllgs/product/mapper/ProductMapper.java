package com.pblgllgs.product.mapper;
/*
 *
 * @author pblgl
 * Created on 04-06-2024
 *
 */

import com.pblgllgs.product.dto.ProductPurchaseResponse;
import com.pblgllgs.product.dto.ProductRequest;
import com.pblgllgs.product.dto.ProductResponse;
import com.pblgllgs.product.entity.Category;
import com.pblgllgs.product.entity.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
    public Product toProduct(ProductRequest productRequest) {
        return Product.builder()
                .id(productRequest.id())
                .name(productRequest.name())
                .price(productRequest.price())
                .description(productRequest.description())
                .availableQuantity(productRequest.availableQuantity())
                .category(Category.builder()
                        .id(productRequest.categoryId())
                        .build())
                .build();
    }

    public ProductResponse toProductResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getAvailableQuantity(),
                product.getPrice(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getCategory().getDescription()
        );
    }

    public ProductPurchaseResponse toProductPurchaseResponse(Product product, double quantity) {
        return new ProductPurchaseResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                quantity
        );
    }
}
