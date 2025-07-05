package com.coolcupp.inventory_service.service;

import com.coolcupp.inventoryService.grpc.InventoryServiceGrpc;
import com.coolcupp.inventoryService.grpc.ProductRequest;
import com.coolcupp.inventoryService.grpc.ProductResponse;
import com.coolcupp.inventory_service.model.Product;
import com.coolcupp.inventory_service.repository.ProductRepository;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class InventoryServiceGrpcServer extends InventoryServiceGrpc.InventoryServiceImplBase {

    private final ProductRepository productRepository;

    public InventoryServiceGrpcServer(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void checkAvailability(ProductRequest request,
                                  StreamObserver<ProductResponse> responseObserver) {

        Product product = productRepository.findById(request.getId()).orElse(null);

        if (product == null) {
            responseObserver.onError(Status.NOT_FOUND
                    .withDescription("product with id " + request.getId() + " not found")
                    .asRuntimeException());
            return;
        }

        ProductResponse response = ProductResponse.newBuilder()
                .setId(product.getId())
                .setName(product.getName())
                .setQuantity(product.getQuantity())
                .setPrice(product.getPrice().toString())
                .setDiscountPercent(product.getDiscountPercent().toString())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
