package com.coolcupp.inventory_service.service;

import com.coolcupp.inventory_service.dto.ProductRequestDTO;
import com.coolcupp.inventory_service.dto.ProductResponseDTO;
import com.coolcupp.inventory_service.mapper.ProductMapper;
import com.coolcupp.inventory_service.model.Product;
import com.coolcupp.inventory_service.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public ResponseEntity<?> getAllProducts() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            return new ResponseEntity<>("Products not found", HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(productMapper.toResponseDTOList(products), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getProductById(Integer id) {
        if (!productRepository.existsById(id)) {
            return new ResponseEntity<>("Product with id " + id + " not found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(productMapper.toResponseDTO(productRepository.findById(id).get()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> createNewProduct(ProductRequestDTO productRequestDTO) {
        Product product = productMapper.toEntityFromRequestDTO(productRequestDTO);
        productRepository.save(product);
        return new ResponseEntity<>(productMapper.toResponseDTO(product), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> deleteProductById(Integer id) {
        if (!productRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        productRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
