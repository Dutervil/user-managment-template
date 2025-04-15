package com.dev.stockApi.service;

import com.dev.stockApi.dto.CategoryDto;
import com.dev.stockApi.dto.ProductDto;
import com.dev.stockApi.dtorequest.CategoryRequest;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryRequest request);
    CategoryDto getCategoryById(Long id);
    List<CategoryDto> getAllCategories();
    CategoryDto updateCategory(Long id, CategoryRequest dto);
    void deleteCategory(Long id);
}
