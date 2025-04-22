package com.dev.stockApi.service;

import com.dev.stockApi.dto.CategoryDto;
import com.dev.stockApi.dto.ProductDto;
import com.dev.stockApi.dtorequest.CategoryRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryRequest categoryRequest, MultipartFile image);
    CategoryDto getCategoryById(Long id);
    List<CategoryDto> getAllCategories();
    CategoryDto updateCategory(String name, MultipartFile image,Long id);
    void deleteCategory(Long id);
}
