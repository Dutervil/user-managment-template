package com.dev.stockApi.mapper;

import com.dev.stockApi.dto.CategoryDto;
import com.dev.stockApi.dto.ProductDto;
import com.dev.stockApi.entity.Category;
import com.dev.stockApi.entity.Product;
import com.dev.stockApi.serviceImpl.ProductServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductDto toDto(Product product) {
        return new ProductDto(
                product.getId(),
                product.getCode(),
                product.getName(),
                product.getUnitPriceExclTax(),
                product.getUnitPriceInclTax(),
                product.getTaxRate(),
                product.getPhotoUrl(),
                product.getQuantity(),
                product.getCategory() != null ? product.getCategory().getId() : null
        );
    }

    public Product toEntity(ProductDto dto, Category category) {
        Product product = new Product();
        product.setId(dto.getId());
        ProductServiceImpl.ExistingProduct(dto, product, category);
        return product;
    }
}
