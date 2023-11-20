package com.sistem.kasir.productservice.service;

import com.sistem.kasir.productservice.dto.ProductRequest;
import com.sistem.kasir.productservice.dto.ProductResponse;
import com.sistem.kasir.productservice.model.Product;
import com.sistem.kasir.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductResponse productResponse;

    public void createProduct(ProductRequest productRequest){
        Product product = Product.builder()
                .productName(productRequest.getProductName())
                .skuCode(productRequest.getSkuCode())
                .price(productRequest.getPrice())
                .build();

        productRepository.save(product);
        log.info("Product {} is saved", product.getProductId());
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();

        return products.stream().map(this::mapToProductResponse).toList();
    }

    public ProductResponse getProductBySkuCode(String skuCode){
        Product product  = productRepository.findBySkuCode(skuCode).orElse(null);

        if(product != null){
            productResponse.setProductId(product.getProductId());
            productResponse.setProductName(product.getProductName());
            productResponse.setSkuCode(product.getSkuCode());
            productResponse.setPrice(product.getPrice());

            return productResponse;
        }else {
            return null;
        }
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
