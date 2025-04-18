package com.dev.stockApi.serviceImpl;

import com.dev.stockApi.dto.ProductDto;
import com.dev.stockApi.entity.Category;
import com.dev.stockApi.entity.Product;
import com.dev.stockApi.exception.ApiException;
import com.dev.stockApi.mapper.ProductMapper;
import com.dev.stockApi.repository.CategoryRepository;
import com.dev.stockApi.repository.ProductRepository;
import com.dev.stockApi.service.FileStorageService;
import com.dev.stockApi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;
    private final FileStorageService fileStorageService;




    @Override
    public ProductDto createProduct(ProductDto dto ) {

        Category category = categoryRepository.findById(dto.getCategoryId()).orElseThrow(() -> new ApiException("Category not found"));
        Product product = productMapper.toEntity(dto, category);
        product.setPhotoUrl("https://cdn-icons-png.flaticon.com/512/679/679720.png");
        Product saved = productRepository.save(product);
        return productMapper.toDto(saved);
    }

    @Override
    public ProductDto getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ApiException("Product not found"));
        return productMapper.toDto(product);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto updateProduct(Long id, ProductDto dto) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new ApiException("Product not found"));
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new ApiException("Category not found"));
        ExistingProduct(dto, existing, category);
        return productMapper.toDto(productRepository.save(existing));
    }

    public static void ExistingProduct(ProductDto dto, Product existing, Category category) {
        existing.setCode(dto.getCode());
        existing.setName(dto.getName());
        existing.setUnitPriceExclTax(dto.getUnitPriceExclTax());
        existing.setUnitPriceInclTax(dto.getUnitPriceInclTax());
        existing.setTaxRate(dto.getTaxRate());
        existing.setPhotoUrl(dto.getPhotoUrl());
        existing.setQuantity(dto.getQuantity());
        existing.setCategory(category);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
