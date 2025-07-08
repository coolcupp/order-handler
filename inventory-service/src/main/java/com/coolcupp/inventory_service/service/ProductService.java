package com.coolcupp.inventory_service.service;

import com.coolcupp.inventory_service.dto.ProductRequestDTO;
import com.coolcupp.inventory_service.dto.ProductResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    ResponseEntity<List<ProductResponseDTO>> getAllProducts();

    ResponseEntity<ProductResponseDTO> getProductById(Long id);

    ResponseEntity<ProductResponseDTO> createNewProduct(ProductRequestDTO productRequestDTO);

    ResponseEntity<ProductResponseDTO> deleteProductById(Long id);
}
