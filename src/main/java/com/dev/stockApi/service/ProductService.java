package com.dev.stockApi.service;

import com.dev.stockApi.dto.ProductDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {

    ProductDto createProduct(ProductDto dto);
    ProductDto getProductById(Long id);
    List<ProductDto> getAllProducts();
    ProductDto updateProduct(Long id, ProductDto dto);
    void deleteProduct(Long id);
}
