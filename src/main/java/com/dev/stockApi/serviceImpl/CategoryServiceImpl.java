package com.dev.stockApi.serviceImpl;

import com.dev.stockApi.dto.CategoryDto;
import com.dev.stockApi.dtorequest.CategoryRequest;
import com.dev.stockApi.entity.Category;
import com.dev.stockApi.repository.CategoryRepository;
import com.dev.stockApi.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;


    @Override
    public CategoryDto createCategory(CategoryRequest request) {
        Category category = new Category();
        category.setName(request.getName());
        category.setImageUrl("https://cdn-icons-png.flaticon.com/512/2919/2919600.png");
        categoryRepository.save(category);
        return CategoryDto.fromEntity(category);
    }

    @Override
    public CategoryDto getCategoryById(Long id) {
        return null;
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return List.of();
    }

    @Override
    public CategoryDto updateCategory(Long id, CategoryRequest dto) {
        return null;
    }

    @Override
    public void deleteCategory(Long id) {

    }
}
