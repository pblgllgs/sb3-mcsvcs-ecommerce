package com.pblgllgs.product.controller;
/*
 *
 * @author pblgl
 * Created on 04-06-2024
 *
 */

import com.pblgllgs.product.dto.ProductPurchaseRequest;
import com.pblgllgs.product.dto.ProductPurchaseResponse;
import com.pblgllgs.product.dto.ProductRequest;
import com.pblgllgs.product.dto.ProductResponse;
import com.pblgllgs.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Integer> createProduct(@Valid @RequestBody ProductRequest productRequest) {
        return new ResponseEntity<>(productService.createProduct(productRequest), HttpStatus.CREATED);
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> purchaseProducts(
            @RequestBody List<ProductPurchaseRequest> requests
    ) {
        return new ResponseEntity<>(productService.purchaseProducts(requests), HttpStatus.OK);
    }

    @GetMapping("/{product-id}")
    public ResponseEntity<ProductResponse> findProductById(
            @PathVariable("product-id") Integer productId
    ) {
        return new ResponseEntity<>(productService.findProductById(productId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAllProducts() {
        return new ResponseEntity<>(productService.findAllProducts(),HttpStatus.OK);
    }
}
