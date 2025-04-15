package com.dev.stockApi.resource;

import com.dev.stockApi.domain.Response;
import com.dev.stockApi.dto.ProductDto;
import com.dev.stockApi.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import static com.dev.stockApi.constant.Constants.FILE_STORAGE;
import static com.dev.stockApi.utils.RequestUtils.getResponse;
import static java.util.Collections.emptyMap;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = { "/product" })
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Response> createProduct(HttpServletRequest request, @RequestBody ProductDto productDto ) {
        return ResponseEntity.ok().body(getResponse(request, Map.of("product", productService.createProduct(productDto)), "New product added", OK));
    }


    @GetMapping("/{id}")
    public ResponseEntity<Response> get(@PathVariable Long id,HttpServletRequest request) {
        return ResponseEntity.ok().body(getResponse(request, Map.of("product", productService.getProductById(id)), "Product retrieved", OK));


    }
    @GetMapping
    public ResponseEntity<Response> all(HttpServletRequest request) {
        return ResponseEntity.ok().body(getResponse(request, Map.of("product", productService.getAllProducts()), "Products retrieved", OK));

    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> update(@PathVariable Long id, @RequestBody ProductDto dto,HttpServletRequest request) {
        return ResponseEntity.ok().body(getResponse(request, Map.of("product", productService.updateProduct(id, dto)), "Product updated", OK));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable Long id,HttpServletRequest request) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().body(getResponse(request,  emptyMap(), "Product deleted", OK));

    }

    @GetMapping(path = "/image/{filename}", produces = { IMAGE_PNG_VALUE, IMAGE_JPEG_VALUE })
    public byte[] getPhoto(@PathVariable("filename") String filename) throws IOException {
        return Files.readAllBytes(Paths.get(FILE_STORAGE + filename));
    }

}
