package com.pblgllgs.product.service;
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
import com.pblgllgs.product.entity.Product;
import com.pblgllgs.product.exception.EntityNotFoundException;
import com.pblgllgs.product.exception.ProductPurchaseException;
import com.pblgllgs.product.mapper.ProductMapper;
import com.pblgllgs.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public Integer createProduct(ProductRequest productRequest) {
        Product product = productMapper.toProduct(productRequest);
        Product savedProduct = productRepository.save(product);
        return savedProduct.getId();
    }

    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> requests) {
        List<Integer> listProductsIds = requests
                .stream()
                .map(ProductPurchaseRequest::productId)
                .toList();
        List<Product> storedProducts = productRepository.findAllByIdInOrderById(listProductsIds);
        if (listProductsIds.size() != storedProducts.size()) {
            throw new ProductPurchaseException("One or more products do not exists");
        }
        List<ProductPurchaseRequest> storedRequest = requests
                .stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();
        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();
        for (int i = 0; i < storedProducts.size(); i++) {
            var product = storedProducts.get(i);
            var productRequest = storedRequest.get(i);
            if (product.getAvailableQuantity() < productRequest.quantity()){
                throw new ProductPurchaseException("Insufficient stock of product: "+ product.getName());
            }
            var newAvailableQuantity = product.getAvailableQuantity() - productRequest.quantity();
            product.setAvailableQuantity(newAvailableQuantity);
            productRepository.save(product);
            purchasedProducts.add(productMapper.toProductPurchaseResponse(product, productRequest.quantity()));
        }
        return purchasedProducts;
    }

    public ProductResponse findProductById(int productId) {
        return productRepository
                .findById(productId)
                .map(productMapper::toProductResponse)
                .orElseThrow(
                        () -> new EntityNotFoundException(String.format("Customer with id %s not found", productId)));
    }

    public List<ProductResponse> findAllProducts() {
        return productRepository.findAll()
                .stream().map(productMapper::toProductResponse)
                .toList();
    }
}
