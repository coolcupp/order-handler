package com.coolcupp.inventory_service.service;

import com.coolcupp.common_lib.exception_handling.exception.NotFoundException;
import com.coolcupp.inventory_service.dto.ProductRequestDTO;
import com.coolcupp.inventory_service.dto.ProductResponseDTO;
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

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        List<Product> products = productRepository.findAll();

        if (products.isEmpty()) {
            log.error("No products found");
            throw new NotFoundException("No products found");
        }

        // list of products -> list of product dto
        List<ProductResponseDTO> productResponseDTOList = products.stream()
                .map(product -> new ProductResponseDTO(
                        product.getId(),
                        product.getName(),
                        product.getQuantity(),
                        product.getPrice(),
                        product.getDiscountPercent()
                ))
                .toList();

        return new ResponseEntity<>(productResponseDTOList, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<ProductResponseDTO> getProductById(Long id) {
        if (!productRepository.existsById(id)) {
            log.info("Product not found with id: {}", id);
            throw new NotFoundException("Product not found with id: " + id);
            // return new ResponseEntity<>("Product with id " + id + " not found", HttpStatus.NOT_FOUND);
        }

        Product product = productRepository.findById(id).get();
        ProductResponseDTO productResponseDTO = new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getQuantity(),
                product.getPrice(),
                product.getDiscountPercent()
        );

        return new ResponseEntity<>(productResponseDTO, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<ProductResponseDTO> createNewProduct(ProductRequestDTO productRequestDTO) {
        log.info("Creating new product with name: {}", productRequestDTO.getName());
        Product product = new Product(
                productRequestDTO.getName(),
                productRequestDTO.getQuantity(),
                productRequestDTO.getPrice(),
                productRequestDTO.getDiscountPercent()
        );

        productRepository.save(product);

        ProductResponseDTO productResponseDTO = new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getQuantity(),
                product.getPrice(),
                product.getDiscountPercent()
        );

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

        ProductResponseDTO productResponseDTO = new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getQuantity(),
                product.getPrice(),
                product.getDiscountPercent()
        );

        log.info("Product deleted: {} !", productResponseDTO.getName());
        return new ResponseEntity<>(productResponseDTO, HttpStatus.OK);
    }
}
