package com.dev.stockApi.resource;

import com.dev.stockApi.domain.Response;
import com.dev.stockApi.dto.ProductDto;
import com.dev.stockApi.dtorequest.CategoryRequest;
import com.dev.stockApi.service.CategoryService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.Map;

import static com.dev.stockApi.utils.RequestUtils.getResponse;
import static java.util.Collections.emptyMap;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = { "/category" })
public class CategoryController {

    private final CategoryService categoryService;


    @PostMapping(consumes = {org.springframework.http.MediaType.ALL_VALUE})
    public ResponseEntity<Response> createProduct(HttpServletRequest request,  @RequestPart("category") CategoryRequest categoryRequest, @RequestParam(value = "image",required = false) MultipartFile image ) {
        return ResponseEntity.ok().body(getResponse(request, Map.of("product", categoryService.createCategory(categoryRequest,image)), "New category added", OK));
    }


    @PatchMapping("/{id}")
    public ResponseEntity<Response> updateProduct(HttpServletRequest request, @RequestParam("name") String name, @RequestParam(value = "image",required = false) MultipartFile image, @PathVariable("id") Long id ) {
        return ResponseEntity.ok().body(getResponse(request, Map.of("product", categoryService.updateCategory(name,image,id)), "Category updated", OK));
    }

    @GetMapping
    public ResponseEntity<Response> getCategory(HttpServletRequest request) {
        return  ResponseEntity.ok().body(getResponse(request, Map.of("product", categoryService.getAllCategories()), "List of Category", OK));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getCategoryById(HttpServletRequest request, @PathVariable("id") Long id) {
        return  ResponseEntity.ok().body(getResponse(request, Map.of("product", categoryService.getCategoryById( id)), "One Category retrieved with ID => "+id , OK));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteCategory(HttpServletRequest request, @PathVariable("id") Long id) {
        this.categoryService.deleteCategory(id);
        return  ResponseEntity.ok().body(getResponse(request,emptyMap(), "Category Deleted by Id => "+id , OK));
    }



}
