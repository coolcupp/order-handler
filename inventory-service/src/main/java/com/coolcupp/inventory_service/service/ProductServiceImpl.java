package com.coolcupp.inventory_service.service;

import com.coolcupp.inventory_service.dto.ProductRequestDTO;
import com.coolcupp.inventory_service.dto.ProductResponseDTO;
import com.coolcupp.inventory_service.model.Product;
import com.coolcupp.inventory_service.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        List<Product> products = productRepository.findAll();

        if (products.isEmpty()) {
            System.out.println("пизда всё пустое");
            // todo throw custom exception
            // return new ResponseEntity<>("Products not found", HttpStatus.NO_CONTENT);
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
        if (productRepository.existsById(id)) {
            System.out.println("пизда всё пустое");
            // todo throw custom exception
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

        return new ResponseEntity<>(productResponseDTO, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ProductResponseDTO> deleteProductById(Long id) {
        if (!productRepository.existsById(id)) {
            // todo throw custom exception
            System.out.println("Пизда: ничего не найдено");
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

        return new ResponseEntity<>(productResponseDTO, HttpStatus.OK);
    }
}
