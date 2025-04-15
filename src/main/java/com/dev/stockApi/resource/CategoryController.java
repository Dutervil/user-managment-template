package com.dev.stockApi.resource;

import com.dev.stockApi.domain.Response;
import com.dev.stockApi.dto.ProductDto;
import com.dev.stockApi.dtorequest.CategoryRequest;
import com.dev.stockApi.service.CategoryService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static com.dev.stockApi.utils.RequestUtils.getResponse;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = { "/category" })
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Response> createProduct(HttpServletRequest request, @RequestBody CategoryRequest categoryRequest ) {
        return ResponseEntity.ok().body(getResponse(request, Map.of("product", categoryService.createCategory(categoryRequest)), "New category added", OK));
    }

}
