package com.coolcupp.inventory_service.service;

import com.coolcupp.inventory_service.dto.ProductRequestDTO;
import com.coolcupp.inventory_service.dto.ProductResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    ResponseEntity<?> getAllProducts();

    ResponseEntity<?> getProductById(Integer id);

    ResponseEntity<?> createNewProduct(ProductRequestDTO productRequestDTO);

    ResponseEntity<?> deleteProductById(Integer id);
}
