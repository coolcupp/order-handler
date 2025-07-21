package com.coolcupp.inventory_service.mapper;

import com.coolcupp.inventory_service.dto.ProductRequestDTO;
import com.coolcupp.inventory_service.dto.ProductResponseDTO;
import com.coolcupp.inventory_service.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductMapper {

    // Product -> ProductResponseDTO
    public ProductResponseDTO toProductResponseDTOFromProduct(Product product) {
        if (product == null) {
            return null;
        }
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();

        productResponseDTO.setId(product.getId());
        productResponseDTO.setName(product.getName());
        productResponseDTO.setQuantity(product.getQuantity());
        productResponseDTO.setPrice(product.getPrice());
        productResponseDTO.setDiscountPercent(product.getDiscountPercent());

        return productResponseDTO;
    }


    // ProductRequestDTO -> Product
    public Product toProductFromProductRequestDTO(ProductRequestDTO productRequestDTO) {
        if (productRequestDTO == null) {
            return null;
        }
        Product product = new Product();

        product.setName(productRequestDTO.getName());
        product.setQuantity(productRequestDTO.getQuantity());
        product.setPrice(productRequestDTO.getPrice());
        product.setDiscountPercent(productRequestDTO.getDiscountPercent());

        return product;
    }


    // List<Product>  -->  List<ProductResponseDTO>
    public List<ProductResponseDTO> toProductResponseDTOListFromProductList(List<Product> products) {
        if (products == null) {
            return null;
        }

        return products.stream()
                .map(product -> new ProductResponseDTO(
                        product.getId(),
                        product.getName(),
                        product.getQuantity(),
                        product.getPrice(),
                        product.getDiscountPercent()
                ))
                .toList();
    }
}
