package com.coolcupp.inventory_service.service.grpc;

import com.coolcupp.common_lib.exception_handling.exception.NotFoundException;
import com.coolcupp.inventoryService.grpc.*;
import com.coolcupp.inventory_service.model.Product;
import com.coolcupp.inventory_service.repository.ProductRepository;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@GrpcService
@Slf4j
public class InventoryServiceGRPCServer extends InventoryServiceGrpc.InventoryServiceImplBase {

    private final ProductRepository productRepository;

    public InventoryServiceGRPCServer(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void checkAvailability(ProductTotalRequest request,
                                  StreamObserver<ProductTotalResponse> responseObserver) {
        log.info("GRPC SERVER: start checking availability");
        // parsing request
        List<ProductRequestItem> requestItems = request.getItemsList();

        // response arrays
        List<ProductResponseItem> availabilityResponseItems = new ArrayList<>();
        List<ProductResponseItem> unavailabilityResponseItems = new ArrayList<>();

        for (ProductRequestItem requestItem : requestItems) {

            Optional<Product> productOptional = productRepository.findById(requestItem.getId());
            if (productOptional.isEmpty()) {
                log.error("GRPC SERVER: product with id {} not found", requestItem.getId());
                throw new NotFoundException("product with id " + requestItem.getId() + " not found");
            }
            Product product = productOptional.get();

            // if storageQuantity < requestedQuantity
            if (requestItem.getRequestedQuantity() > product.getQuantity()) {
                // add to unavailability items
                unavailabilityResponseItems.add(
                        ProductResponseItem.newBuilder()
                                .setId(product.getId())
                                .setName(product.getName())
                                .setRequestedQuantity(requestItem.getRequestedQuantity())
                                .setPrice(product.getPrice().toString())
                                .setDiscountPercent(product.getDiscountPercent().toString())
                                .setQuantityInStorage(product.getQuantity())
                                .setIsAvailability(false)
                                .build()
                );
            }
            // if storageQuantity > requestedQuantity
            if (requestItem.getRequestedQuantity() < product.getQuantity()) {
                // add to availability items
                availabilityResponseItems.add(
                        ProductResponseItem.newBuilder()
                                .setId(product.getId())
                                .setName(product.getName())
                                .setRequestedQuantity(requestItem.getRequestedQuantity())
                                .setPrice(product.getPrice().toString())
                                .setDiscountPercent(product.getDiscountPercent().toString())
                                .setQuantityInStorage(product.getQuantity())
                                .setIsAvailability(true)
                                .build()
                );
            }
        }

        // building response
        ProductTotalResponse response = ProductTotalResponse.newBuilder()
                .addAllAvailabilityItems(availabilityResponseItems)
                .addAllUnavailabilityItems(unavailabilityResponseItems)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
        log.info("GRPC SERVER: availability check completed");
    }
}
