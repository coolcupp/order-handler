package com.coolcupp.inventory_service.controller;

import com.coolcupp.inventory_service.dto.ProductRequestDTO;
import com.coolcupp.inventory_service.dto.ProductResponseDTO;
import com.coolcupp.inventory_service.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api")
@Slf4j
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("products")
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        log.info("REQUEST ACCEPTED: get all products");
        return productService.getAllProducts();
    }

    @GetMapping("products/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable("id") Long id) {
        log.info("REQUEST ACCEPTED: get product by id: {}", id);
        return productService.getProductById(id);
    }

    @PostMapping("products")
    public ResponseEntity<ProductResponseDTO> createNewProduct(@RequestBody ProductRequestDTO productRequestDTO) {
        log.info("REQUEST ACCEPTED: create new product with name: {}", productRequestDTO.getName());
        return productService.createNewProduct(productRequestDTO);
    }

    @DeleteMapping("products/{id}")
    public ResponseEntity<ProductResponseDTO> deleteProductById(@PathVariable("id") Long id) {
        log.info("REQUEST ACCEPTED: delete product by id: {}", id);
        return productService.deleteProductById(id);
    }
}
