package com.dev.stockApi.repository;

import com.dev.stockApi.dto.CategoryDto;
import com.dev.stockApi.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository  extends JpaRepository<Category, Long> {
    Category findCategoryById(Long id);
}
