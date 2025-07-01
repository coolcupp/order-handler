package com.coolcupp.inventory_service.controller;

import com.coolcupp.inventory_service.dto.ProductRequestDTO;
import com.coolcupp.inventory_service.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("products")
    public ResponseEntity<?> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("products/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") Integer id) {
        return productService.getProductById(id);
    }

    @PostMapping("products")
    public ResponseEntity<?> createNewProduct(@RequestBody ProductRequestDTO productRequestDTO) {
        return productService.createNewProduct(productRequestDTO);
    }

    @DeleteMapping("products/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable("id") Integer id) {
        return productService.deleteProductById(id);
    }
}
