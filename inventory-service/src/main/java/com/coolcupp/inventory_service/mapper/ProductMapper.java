package com.coolcupp.inventory_service.mapper;

import com.coolcupp.inventory_service.dto.ProductRequestDTO;
import com.coolcupp.inventory_service.dto.ProductResponseDTO;
import com.coolcupp.inventory_service.model.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductResponseDTO toResponseDTO(Product product);

    Product toEntityFromRequestDTO(ProductRequestDTO productRequestDTO);

    List<ProductResponseDTO> toResponseDTOList(List<Product> productList);

    List<Product> toEntityListFromResponseDTOList(List<ProductResponseDTO> productResponseDTOList);
}
