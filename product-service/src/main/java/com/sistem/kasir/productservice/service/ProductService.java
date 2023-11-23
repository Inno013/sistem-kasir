package com.sistem.kasir.productservice.service;

import com.sistem.kasir.productservice.dto.ProductRequest;
import com.sistem.kasir.productservice.dto.ProductResponse;
import com.sistem.kasir.productservice.model.Product;
import com.sistem.kasir.productservice.repository.ProductRepository;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    @Transactional
    public void createProduct(ProductRequest productRequest){
        Product product = Product.builder()
                .productName(productRequest.getProductName())
                .skuCode(productRequest.getSkuCode())
                .price(productRequest.getPrice())
                .build();

        productRepository.save(product);
    }
    @Transactional(readOnly = true)
    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();

        return products.stream().map(this::mapToProductResponse).toList();
    }
    @Transactional(readOnly = true)
    public ProductResponse getProductBySkuCode(String skuCode){
        Product product  = productRepository.findBySkuCode(skuCode).orElse(null);

        if(product != null){
            return mapToProductResponse(product);
        }else {
            return null;
        }
    }
    @Transactional
    public ProductResponse deleteProduct(ProductRequest productRequest){
        Product deletedProduct = productRepository.findById(productRequest.getProductId()).orElse(null);

        if (deletedProduct != null) {
            productRepository.deleteById(productRequest.getProductId());
        }
        return mapToProductResponse(deletedProduct);

    }
    @Transactional
    public void updateProduct(ProductRequest productRequest){
        Product product = productRepository.findById(productRequest.getProductId()).orElseThrow(() -> new NotFoundException("Product Not Found"));
        product.setProductName(productRequest.getProductName());
        product.setSkuCode(productRequest.getSkuCode());
        product.setPrice(productRequest.getPrice());
        productRepository.save(product);
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .skuCode(product.getSkuCode())
                .price(product.getPrice())
                .build();
    }
}
