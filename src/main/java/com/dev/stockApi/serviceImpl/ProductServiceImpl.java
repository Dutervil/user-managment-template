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

import java.util.stream.Collectors;


import static com.dev.stockApi.constant.Constants.DEFAULT_PRODUCT_IMAGE;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;
    private final FileStorageService fileStorageService;




    @Override
    public ProductDto createProduct(ProductDto dto, MultipartFile image ) throws ApiException {

        Category category = categoryRepository.findById(dto.getCategoryId()).orElseThrow(() -> new ApiException("Category not found"));
        Product product = productMapper.toEntity(dto, category);
        product.setPhotoUrl( image!=null ? fileStorageService.uploadPhoto(dto.getId()+"",  image) : DEFAULT_PRODUCT_IMAGE);
        product.setQuantity(0);
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
    public ProductDto updateProduct(Long id, ProductDto dto,MultipartFile image) {
        Product existing = productRepository.findById(id).orElseThrow(() -> new ApiException("Product not found"));
        Category category = categoryRepository.findById(dto.getCategoryId()).orElseThrow(() -> new ApiException("Category not found"));

        existing.setCode(dto.getCode());
        existing.setName(dto.getName());
        existing.setUnitPriceExclTax(dto.getUnitPriceExclTax());
        existing.setUnitPriceInclTax(dto.getUnitPriceInclTax());
        existing.setTaxRate(dto.getTaxRate());
        existing.setPhotoUrl( image!=null ? fileStorageService.uploadPhoto(dto.getId()+"" ,image ) : existing.getPhotoUrl());
        existing.setCategory(category);
        return productMapper.toDto(productRepository.save(existing));
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
