package com.dev.stockApi.dto;


import com.dev.stockApi.entity.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    private Long id;
    private String name;
    private String imageUrl;

    @JsonIgnore
    private List<ProductDto>products;



    public static CategoryDto fromEntity(Category category) {
        if (category == null) {
            return null;
        }

        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .imageUrl(category.getImageUrl())
                .build();
    }
 public static Category toEntity(CategoryDto categoryDto) {
        if (categoryDto == null) {
            return null;
        }
     Category category = new Category();
        category.setId(categoryDto.getId());
        category.setName(categoryDto.getName());
        category.setImageUrl(categoryDto.getImageUrl());
     return category;
 }
}
