package com.dev.stockApi.mapper;

import com.dev.stockApi.dto.CategoryDto;
import com.dev.stockApi.dto.ProductDto;
import com.dev.stockApi.entity.Category;
import com.dev.stockApi.entity.Product;
import com.dev.stockApi.serviceImpl.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

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
        product.setCode(dto.getCode());
        product.setName(dto.getName());
        product.setUnitPriceExclTax(dto.getUnitPriceExclTax());
        product.setUnitPriceInclTax(dto.getUnitPriceInclTax());
        product.setTaxRate(dto.getTaxRate());
        product.setPhotoUrl( dto.getPhotoUrl());
        product.setCategory(category);
        product.setQuantity(dto.getQuantity());

        return product;
    }
}
