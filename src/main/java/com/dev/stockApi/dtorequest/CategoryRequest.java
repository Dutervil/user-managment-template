package com.dev.stockApi.dtorequest;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryRequest {

    @NotEmpty(message = "Category name cannot be empty or null")
    private String name;
    private Long categoryId;
}
