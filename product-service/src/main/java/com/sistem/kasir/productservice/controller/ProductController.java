package com.sistem.kasir.productservice.controller;

import com.sistem.kasir.productservice.dto.ProductRequest;
import com.sistem.kasir.productservice.dto.ProductResponse;
import com.sistem.kasir.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> createProduct(@RequestBody ProductRequest productRequest){
        try {
            productService.createProduct(productRequest);
            return ResponseEntity.ok("Product Succes Create");
        }catch (DataIntegrityViolationException e){
            return ResponseEntity.badRequest().body("Error creating product: Data integrity violation");
        }catch (Exception e) {
            return ResponseEntity.status(500).body("Error creating product: " + e.getMessage());
        }
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/{skuCode}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse getProductBySkuCode(@PathVariable("skuCode") String skuCode){
        log.info("Request received for skuCode: {}", skuCode);
        return  productService.getProductBySkuCode(skuCode);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteProductById(@RequestBody ProductRequest productRequest){
        ProductResponse deleleTrue = productService.deleteProduct(productRequest);
        if(deleleTrue != null){
            return ResponseEntity.ok("Product " + productRequest.getProductName() + " Sukses Delete");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateProduct(@RequestBody ProductRequest productRequest){
        try {
            productService.updateProduct(productRequest);
            return ResponseEntity.ok("Product has been Update");
        }catch (DataIntegrityViolationException e){
            return ResponseEntity.badRequest().body("Error creating product: Data integrity violation");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
    }

}
