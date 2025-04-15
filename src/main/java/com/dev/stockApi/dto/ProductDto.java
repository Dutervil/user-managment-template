package com.dev.stockApi.dto;


import com.dev.stockApi.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Long id;
    private String code;
    private String name;
    private BigDecimal unitPriceExclTax;
    private BigDecimal unitPriceInclTax;
    private BigDecimal taxRate;
    private String photoUrl;
    private Integer quantity;
    private Long categoryId;


    public static ProductDto fromEntity(Product product) {
        if (product == null) {
            return null;
        }
        return ProductDto.builder()
                .id(product.getId())
                .code(product.getCode())
                .name(product.getName())
                .quantity(product.getQuantity())
                .unitPriceExclTax(product.getUnitPriceExclTax())
                .unitPriceInclTax(product.getUnitPriceInclTax())
                .taxRate(product.getTaxRate())
                .photoUrl(product.getPhotoUrl())
                .categoryId(product.getCategory() != null ? product.getCategory().getId() : null)
                .build();
    }


    public static Product toEntity(ProductDto productDto) {
        if (productDto == null) {
            return null;
        }
        Product product = new Product();
        product.setId(productDto.getId());
        product.setCode(productDto.getCode());
        product.setName(productDto.getName());
        product.setQuantity(productDto.getQuantity());
        product.setUnitPriceExclTax(productDto.getUnitPriceExclTax());
        product.setUnitPriceInclTax(productDto.getUnitPriceInclTax());
        product.setTaxRate(productDto.getTaxRate());
        product.setPhotoUrl(productDto.getPhotoUrl());
        return product;
    }

}
