package com.coolcupp.inventory_service.service;

import com.coolcupp.common_lib.exception_handling.exception.NotFoundException;
import com.coolcupp.inventory_service.dto.ProductRequestDTO;
import com.coolcupp.inventory_service.dto.ProductResponseDTO;
import com.coolcupp.inventory_service.mapper.ProductMapper;
import com.coolcupp.inventory_service.model.Product;
import com.coolcupp.inventory_service.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }


    @Override
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        List<Product> products = productRepository.findAll();

        if (products.isEmpty()) {
            log.error("No products found");
            throw new NotFoundException("No products found");
        }

        List<ProductResponseDTO> productResponseDTOList =
                productMapper.toProductResponseDTOListFromProductList(products);

        return new ResponseEntity<>(productResponseDTOList, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<ProductResponseDTO> getProductById(Long id) {
        if (!productRepository.existsById(id)) {
            log.warn("Product not found with id: {}", id);
            throw new NotFoundException("Product not found with id: " + id);
        }

        // product -> productResponseDTO
        Product product = productRepository.findById(id).get();
        ProductResponseDTO productResponseDTO = productMapper.toProductResponseDTOFromProduct(product);

        return new ResponseEntity<>(productResponseDTO, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<ProductResponseDTO> createNewProduct(ProductRequestDTO productRequestDTO) {
        log.info("Creating new product with name: {}", productRequestDTO.getName());

        Product product = productMapper.toProductFromProductRequestDTO(productRequestDTO);

        productRepository.save(product);

        ProductResponseDTO productResponseDTO = productMapper.toProductResponseDTOFromProduct(product);

        log.info("Product created: {}", productResponseDTO.getName());
        return new ResponseEntity<>(productResponseDTO, HttpStatus.CREATED);
    }


    @Override
    public ResponseEntity<ProductResponseDTO> deleteProductById(Long id) {
        log.info("Deleting product with id: {} ...", id);
        if (!productRepository.existsById(id)) {
            log.error("Product not found with id: {}", id);
            throw new NotFoundException("Product not found with id: " + id);
        }

        Product product = productRepository.findById(id).get();
        productRepository.deleteById(id);

        ProductResponseDTO productResponseDTO = productMapper.toProductResponseDTOFromProduct(product);

        log.info("Product deleted: {} !", productResponseDTO.getName());
        return new ResponseEntity<>(productResponseDTO, HttpStatus.OK);
    }
}
