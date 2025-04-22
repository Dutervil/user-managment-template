package com.dev.stockApi.serviceImpl;

import com.dev.stockApi.dto.CategoryDto;
import com.dev.stockApi.dtorequest.CategoryRequest;
import com.dev.stockApi.entity.Category;
import com.dev.stockApi.exception.ApiException;
import com.dev.stockApi.repository.CategoryRepository;
import com.dev.stockApi.service.CategoryService;
import com.dev.stockApi.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import static com.dev.stockApi.constant.Constants.DEFAULT_CATEGORY_IMAGE;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final FileStorageService fileStorageService;

    @Override
    public CategoryDto createCategory(CategoryRequest categoryRequest, MultipartFile image) throws ApiException {
        Category category = new Category();
        category.setName(categoryRequest.getName());
        category.setImageUrl( image!=null ? fileStorageService.uploadPhoto(categoryRequest.getName(),  image) : DEFAULT_CATEGORY_IMAGE);
        categoryRepository.save(category);
        return CategoryDto.fromEntity(category);
    }

    @Override
    public CategoryDto getCategoryById(Long id) {
        Category category = this.categoryRepository.findCategoryById(id);
        if (category == null) throw new ApiException("Category not found");
        return CategoryDto.fromEntity(category);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream().map(CategoryDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public CategoryDto updateCategory(String name, MultipartFile image, Long id) {

        Category category = this.categoryRepository.findCategoryById(id);
        if (category == null) throw new ApiException("Category not found");
        category.setName(name);
        category.setImageUrl( image!=null ? fileStorageService.uploadPhoto(name,  image) : category.getImageUrl());
        categoryRepository.save(category);
        return CategoryDto.fromEntity(category);

    }


    @Override
    public void deleteCategory(Long id) {
        Category category = this.categoryRepository.findCategoryById(id);
        if (category == null) {
            throw new ApiException("Category not found.");
        }

        if (category.getProducts() != null && !category.getProducts().isEmpty()) {
            throw new ApiException("This Category has products associated with it. Please delete the products before deleting the category.");
        }

        this.categoryRepository.deleteById(id);
    }
}
